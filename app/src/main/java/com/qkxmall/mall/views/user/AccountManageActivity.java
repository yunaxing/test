package com.qkxmall.mall.views.user;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.Task.xTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.address.AccountAddressManageActivity;
import com.qkxmall.mall.views.fragment.FragmentBlankActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AccountManageActivity extends Activity {
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.png";

    private Context context;

    private ImageView backup = null;
    private RelativeLayout changeUserPicture = null;
    private RelativeLayout changeUserName = null;
    private RelativeLayout manageUserAddress = null;
    private RelativeLayout accountSecurity = null;
    private RelativeLayout changeBindPhone = null;

    private Button exitCurrentAccount = null;
    private TextView name = null;
    private TextView nickName = null;
    private TextView phone = null;
    private TextView address = null;
    private TextView status = null;
    private SimpleDraweeView headImage = null;

    private Bitmap bitmap1;
    String path = "";

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_SOME_FEATURES_PERMISSIONS: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int hasReadPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            List<String> permissions = new ArrayList<String>();
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else {
//                preferencesUtility.setString("storage", "true");
            }
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            } else {
//                preferencesUtility.setString("storage", "true");
            }
            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

            } else {
//                preferencesUtility.setString("storage", "true");
            }

            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);
            }
        }
    }
    public final static int REQUEST_CODE_SOME_FEATURES_PERMISSIONS=0x0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initPermission();
        init();
        context = AccountManageActivity.this;
        LOD lod = new LOD(context);

        String address = API.USER_INFO+"&uid="+lod.get("USER_INFO","UID","");
        LoadUserInfo loadUserInfo = new LoadUserInfo();
        BackgroundTask backgroundTask = new BackgroundTask(context,address,loadUserInfo);
        backgroundTask.doInBackground();

        backup.setOnClickListener(new OnClick());
        changeUserPicture.setOnClickListener(new OnClick());
        changeUserName.setOnClickListener(new OnClick());
        manageUserAddress.setOnClickListener(new OnClick());
        accountSecurity.setOnClickListener(new OnClick());
        changeBindPhone.setOnClickListener(new OnClick());
        exitCurrentAccount.setOnClickListener(new OnClick());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.navigation);
        changeUserPicture = (RelativeLayout) findViewById(R.id.account_manage_change_user_picture);
        changeUserName = (RelativeLayout) findViewById(R.id.account_manage_change_user_name);
        manageUserAddress = (RelativeLayout) findViewById(R.id.account_manage_manage_address);
        accountSecurity = (RelativeLayout) findViewById(R.id.account_manage_account_security);
        exitCurrentAccount = (Button) findViewById(R.id.account_manage_exit_current_account);
        changeBindPhone = (RelativeLayout) findViewById(R.id.change_bind_phone);

        headImage = (SimpleDraweeView) findViewById(R.id.headImage);
        phone = (TextView) findViewById(R.id.account);
        //name = (TextView) findViewById(R.id.name);
        nickName = (TextView) findViewById(R.id.nickName);
        address = (TextView) findViewById(R.id.address);
        status = (TextView) findViewById(R.id.status);

    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            switch (view.getId()){
                case R.id.navigation:
                    AccountManageActivity.this.finish();
                    break;
                case R.id.account_manage_change_user_picture:
                    final AlertDialog alertDialogChangeUserPicture = new AlertDialog.Builder(AccountManageActivity.this).create();
                    alertDialogChangeUserPicture.show();
                    final Window alertWindow = alertDialogChangeUserPicture.getWindow();
                    alertWindow.setContentView(R.layout.alert_change_user_picture_layout);//对话框布局
                    alertWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_white_button_background));//样式文件
                    LinearLayout changeByAlbum = (LinearLayout) alertWindow.findViewById(R.id.change_user_picture_by_album);//相机按钮
                    LinearLayout changeByCamera = (LinearLayout) alertWindow.findViewById(R.id.change_user_picture_by_camera);//相册按钮
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
                            if (isSdcardExisting()) {//判断是否有sd卡
                                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
//                                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
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
                    startActivity(new Intent(AccountManageActivity.this,AccountAddressManageActivity.class));
                    break;
                case R.id.account_manage_account_security:
                    startActivity(new Intent(AccountManageActivity.this,ChangePasswordActivity.class));
                    break;
                case R.id.account_manage_exit_current_account:
                    SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLogin",false);
                    editor.commit();
                    Toast.makeText(AccountManageActivity.this,"退出成功!",Toast.LENGTH_SHORT).show();
                    AccountManageActivity.this.finish();
                    break;
                case R.id.change_bind_phone:
                    startActivity(new Intent(AccountManageActivity.this, FragmentBlankActivity.class));
//                    startActivity(new Intent(AccountManageActivity.this,ChangeBingPhoneActivity.class));
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null || "".equals(data))
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE: {
                    Uri uri = data.getData();
                    ContentResolver cr = this.getContentResolver();
//                    try {
//                    /* 将Bitmap设定到ImageView */
//                        bitmap1 = BitmapFactory.decodeStream(cr.openInputStream(uri));
//                        headImage.setImageBitmap(bitmap1);
////                        Glide.with(context).load(bitmap1).into(headImage);
//
//                    } catch (FileNotFoundException e) {
//                        Log.e("Exception", e.getMessage(), e);
//                    }
//                    //输出Uri
//                    Log.e("UriInfo", "");
//
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = managedQuery(uri, proj, null, null, null);
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    cursor.moveToFirst();
//                    path = cursor.getString(column_index);
                    path = getImageAbsolutePath(this,uri);
                    System.out.println("！！！！！！！！！！！！· path"+path);
                    if (path != null)
                        if (!path.equals(""))
                            Log.e("ImagePath:",path);
                    headImage.setImageURI(data.getData());
                }
                    break;
                case CAMERA_REQUEST_CODE: {
                    if (isSdcardExisting()) {
                        saveImage(data);
                        Log.e("ImagePathPhotograph:", path);
                        Uri uri = Uri.fromFile(new File(path));
                        headImage.setImageURI(uri);
                    } else {
                        Toast.makeText(AccountManageActivity.this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                }
                    break;
                case RESIZE_REQUEST_CODE:
                    if (data != null) {
//                        showResizeImage(data);
                    }
                    break;
            }
            uploadImage(path);
        }
    }

    private boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    private class LoadUserInfo extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            headImage.setImageURI(Uri.parse(jsonObject.getString("ico")));

//                            BitmapHelper.downloadBitmap(AccountManageActivity.this,)

                            //name.setText(jsonObject.getString("username"));
                            nickName.setText(jsonObject.getString("nickname"));
                            phone.setText(jsonObject.getString("mobile_phone"));
                            System.out.println("+++++++++++++++++++++++++++++++++++++用户名：" + jsonObject.getString("username"));
//                            address.setText(
//                                    jsonObject.getJSONObject("address").getString("provincename")
//                                    +jsonObject.getJSONObject("address").getString("cityname")
//                                    +jsonObject.getJSONObject("address").getString("districtname")
//                                    +jsonObject.getJSONObject("address").getString("detail"));

                            //设置地址
                            String provincename = jsonObject.getJSONObject("address").getString("provincename");
                            String cityname = jsonObject.getJSONObject("address").getString("cityname");
                            String districtname = jsonObject.getJSONObject("address").getString("districtname");
                            String detail = jsonObject.getJSONObject("address").getString("detail");

                            if (provincename.equals("null"))
                            {
                                provincename = "";
                            }

                            if (cityname.equals("null"))
                            {
                                cityname = "";
                            }

                            if (districtname.equals("null"))
                            {
                                districtname = "";
                            }

                            if (detail.equals("null"))
                            {
                                detail = "";
                            }

                            address.setText(provincename + cityname + districtname + detail);

                            Log.e("headImg",API.ADD+jsonObject.getString("ico"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private void saveImage(Intent data) {
        String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        FileOutputStream b = null;
        //???????????????????????????????为什么不能直接保存在系统相册位置呢？？？？？？？？？？？？
        File file = new File(Environment.getExternalStorageDirectory()+"/DCIM/create/");
        file.mkdirs();// 创建文件夹
        String fileName = Environment.getExternalStorageDirectory()+"/DCIM/create/"+name;
        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
            path = fileName;
            Log.e("ImagePathSave:",path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(String path){
        Log.e("aiitec", "path:"+path);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("文件上传中...");
        progressDialog.show();
        String url = API.UPLOAD_IMAGE;
//        String url="https://www.qkxmall.com/api/android/android.php";
        org.xutils.http.RequestParams requestParams = new org.xutils.http.RequestParams(url);
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", new File(path));
        UploadHandler uploadHandler = new UploadHandler(progressDialog);
        xTask xTask = new xTask(context,uploadHandler,url,requestParams);
        xTask.doing();
    }
    private class UploadHandler extends Handler{
        ProgressDialog progressDialog;

        public UploadHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case xTask.SUCCESS:{
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        String path = bundle.getString("result");
                        System.out.println("!!!!!!!!!!!!!!!!! result" + bundle.getString("result"));

                        LOD lod = new LOD(context);
                        ProgressDialog dialog = new ProgressDialog(context);
                        dialog.setMessage("头像更改中...");
                        dialog.show();
                        String url = API.UPDATE_USER_INFO+"&uid="+lod.get("USER_INFO","UID","")+"&field=ico&v="/*+API.ADD*/+bundle.getString("result");
                        Log.e("Upload:Result",path);
                        UpdateICO updateICO = new UpdateICO(dialog);
                        BackgroundTask backgroundTask = new BackgroundTask(context,url,updateICO);
                        backgroundTask.doInBackground();
                    }
                }
                    break;
                case xTask.ERROR:{
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
                case xTask.CANCELLED:{
                    progressDialog.dismiss();
                }
                    break;
                case xTask.FINISHED:{
                    progressDialog.dismiss();
                }
                    break;
            }
        }
    }

    private class UpdateICO extends Handler{
        ProgressDialog progressDialog;

        public UpdateICO(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                    break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                }
                    break;
            }
        }
    }



    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     * @param context
     * @param imageUri
     * @author yaoxing
     * @date 2014-10-12
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[] { split[1] };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }


        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }



}
