package com.qkxmall.mall.views.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.address.AccountAddressManageActivity;
import com.qkxmall.mall.views.user.ChangePasswordActivity;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Sunshine on 10/10/2015.
 */
public class AccountFragment extends Fragment {
    Context context;


    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";

    private ImageView backup = null;
    private RelativeLayout changeUserPicture = null;
    private RelativeLayout changeUserName = null;
    private RelativeLayout manageUserAddress = null;
    private RelativeLayout accountSecurity = null;
    private Button exitCurrentAccount = null;
    private TextView accountManageUserName = null;
    private RelativeLayout changeBindPhone = null;
    private Bitmap bitmap1;
    private ImageView imageView = null;

    public AccountFragment() {
    }

    public void init(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account,container,false);
        init(rootView);

        backup.setOnClickListener(new OnClickListeners());
        changeUserPicture.setOnClickListener(new OnClickListeners());
        changeUserName.setOnClickListener(new OnClickListeners());
        manageUserAddress.setOnClickListener(new OnClickListeners());
        accountSecurity.setOnClickListener(new OnClickListeners());
        changeBindPhone.setOnClickListener(new OnClickListeners());


        return rootView;
    }

    private void init(View rootView) {
        backup = (ImageView) rootView.findViewById(R.id.account_manage_backup);
        changeUserPicture = (RelativeLayout) rootView.findViewById(R.id.account_manage_change_user_picture);
        changeUserName = (RelativeLayout) rootView.findViewById(R.id.account_manage_change_user_name);
        manageUserAddress = (RelativeLayout) rootView.findViewById(R.id.account_manage_manage_address);
        accountSecurity = (RelativeLayout) rootView.findViewById(R.id.account_manage_account_security);
        exitCurrentAccount = (Button) rootView.findViewById(R.id.account_manage_exit_current_account);
        accountManageUserName = (TextView) rootView.findViewById(R.id.account_manage_user_name);
        changeBindPhone = (RelativeLayout) rootView.findViewById(R.id.change_bind_phone);
        imageView = (ImageView) rootView.findViewById(R.id.user_pic);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.account_manage_backup:
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.popBackStack();
                    break;
                case R.id.account_manage_change_user_picture:
                    final AlertDialog alertDialogChangeUserPicture = new AlertDialog.Builder(context.getApplicationContext()).create();
                    alertDialogChangeUserPicture.show();
                    final Window alertWindow = alertDialogChangeUserPicture.getWindow();
                    alertWindow.setContentView(R.layout.alert_change_user_picture_layout);
                    alertWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_white_button_background));
                    LinearLayout changeByAlbum = (LinearLayout) alertWindow.findViewById(R.id.change_user_picture_by_album);
                    LinearLayout changeByCamera = (LinearLayout) alertWindow.findViewById(R.id.change_user_picture_by_camera);
                    changeByAlbum.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                        /* 开启Pictures画面Type设定为image */
                            intent.setType("image/*");
                        /* 使用Intent.ACTION_GET_CONTENT这个Action */
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                         /* 取得相片后返回本画面 */
                            startActivityForResult(intent, IMAGE_REQUEST_CODE);
                            alertDialogChangeUserPicture.cancel();
                        }
                    });
                    changeByCamera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isSdcardExisting()) {
                                Intent cameraIntent = new Intent(
                                        "android.media.action.IMAGE_CAPTURE");
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
                                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                            } else {
                                Toast.makeText(v.getContext(), "请插入sd卡", Toast.LENGTH_LONG)
                                        .show();
                            }
                            alertDialogChangeUserPicture.cancel();
                        }
                    });
                    break;
                case R.id.account_manage_manage_address:
                    startActivity(new Intent(context,AccountAddressManageActivity.class));
                    break;
                case R.id.account_manage_account_security:
                    startActivity(new Intent(context,ChangePasswordActivity.class));
                    break;
                case R.id.account_manage_exit_current_account:
                    Toast.makeText(context,"退出成功!",Toast.LENGTH_SHORT).show();
                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();
                    break;
                case R.id.change_bind_phone:
                    startActivity(new Intent(context,FragmentBlankActivity.class));
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    Uri uri = data.getData();
                    ContentResolver cr = context.getContentResolver();
                    try {
                    /* 将Bitmap设定到ImageView */
                        BitmapFactory.Options newOpts = new BitmapFactory.Options();
                        newOpts.inJustDecodeBounds = false;
                        newOpts.inSampleSize = 2;// 设置采样率
                        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 该模式是默认的,可不设
                        newOpts.inPurgeable = true;// 同时设置才会有效
                        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收
//                        BitmapFactory.decodeResourceStream()
                        bitmap1 = BitmapFactory.decodeStream(cr.openInputStream(uri), null, newOpts);
                        imageView.setImageBitmap(bitmap1);
                    } catch (FileNotFoundException e) {
                        Log.e("Exception", e.getMessage(), e);
                    }
                    resizeImage(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());
                    } else {
                        Toast.makeText(context, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;

                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    private void showResizeImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            imageView.setImageDrawable(drawable);
        }
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

}
