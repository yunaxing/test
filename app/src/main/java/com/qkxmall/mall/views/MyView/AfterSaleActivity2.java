package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qkxmall.mall.BitmapUtils;
import com.qkxmall.mall.R;
import com.qkxmall.mall.SelectImageUtils;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.Task.xTask;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2016/5/16.
 */
public class AfterSaleActivity2 extends Activity implements View.OnClickListener {

    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header1.png";
    private ImageView image;
    private Button btn;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private EditText edtv;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

//    private Bitmap bitmap1;
//    String path = "";

    String uid = "";
    String orderid = "";
    String delivery = "";
    String goodsID = "";
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale);
        init();
        image.setOnClickListener(this);
        btn.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);


    }

    public void init() {
        image = (ImageView) findViewById(R.id.after_sale_backup);
        btn = (Button) findViewById(R.id.after_sale_submit);
        image1 = (ImageView) findViewById(R.id.iv1);
        image2 = (ImageView) findViewById(R.id.iv2);
        image3 = (ImageView) findViewById(R.id.iv3);
        edtv = (EditText) findViewById(R.id.after_sale_message);
        radioGroup = (RadioGroup) findViewById(R.id.after_sale_type);


        uid = getIntent().getStringExtra("uid");//用户id
        orderid = getIntent().getStringExtra("orderid");//订单号
        delivery = getIntent().getStringExtra("delivery");//快递名称
        goodsID = getIntent().getStringExtra("deliverysn");//快递单号
        id = getIntent().getStringExtra("id");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null || "".equals(data))
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String path = SelectImageUtils.getPathByUri4kitkat(this, uri);
                Bitmap bitmap1 = BitmapUtils.compressImageFromFile(this, path, 2);
                switch (requestCode) {
                    case 11: {
                        image1.setImageBitmap(bitmap1);
                    }
                    break;
                    case 12: {
                        image2.setImageBitmap(bitmap1);
                    }
                    break;
                    case 13: {
                        image3.setImageBitmap(bitmap1);
                    }
                    break;
                }
                new AsyncTask<Bitmap, Void, String>() {
                    @Override
                    protected String doInBackground(Bitmap... params) {//这里有点耗时，所以加了AsyncTask
                        //        filePath 是压缩过后的文件路径
                        String filePath = BitmapUtils.bitmapToFile(AfterSaleActivity2.this, params[0]);
                        return filePath;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        uploadImage(s);
                    }
                }.execute(bitmap1);
//                uploadImage(path);
            }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.after_sale_backup:
                finish();
                break;
            case R.id.after_sale_submit://提交按钮

                radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String url = "";
                if (radioButton != null) {
                    String num = "";
                    switch (radioButton.getText().toString().trim()) {
                        case "换货":
                            num = "2";
                            break;
                        case "维修":
                            num = "3";
                            break;
                        case "退款":
                            num = "4";
                            break;
                        case "退款退货":
                            num = "1";
                            break;
                    }

                    String text = edtv.getText().toString().trim();
                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("加载中...");
                    progressDialog.show();
                    PayHandler payHandler = new PayHandler(progressDialog);
                    url = "http://www.qkxmall.com/?m=api&c=order&a=orderreturn&uid=" + uid + "&orderid=" + orderid + "&rtype=" + num + "&delivery=" + delivery
                            + "&deliverysn=" + goodsID + "&descript=" + text + "&img=" + arrS + "&ordersn=" + id;

                    System.out.println("!!!!!!!!!!!!!!!!1111 url:" + url);
                    BackgroundTask backgroundTask = new BackgroundTask(this, url, payHandler);
                    backgroundTask.doInBackground();


                } else {
                    Toast.makeText(this, "请选择售后类型", Toast.LENGTH_LONG).show();
                }


//                String text=edtv.getText().toString().trim();
//                ProgressDialog progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage("加载中...");
//                progressDialog.show();
//                PayHandler payHandler = new PayHandler(progressDialog);
//                String url="";
//                if (getIntent().getBooleanExtra("yunbl",false)){
//                    url="http://www.qkxmall.com/?m=api&c=cloud&a=cloudcomment&uid="+uid+"&cid="+orderID+"&term="+productID
//                            +"&oid="+goodsID+"&content="+text+"&img="+arrS;//+"&ms="+rating1+"&wl="+rating2;
//                }else{
//                    url = "http://www.qkxmall.com/?m=api&c=goods&a=goodscomment&uid="+uid+"&gid="+goodsID+"&pid="+productID
//                            +"&oid="+orderID+"&content="+text+"&img="+arrS+"&ms="+rating1+"&wl="+rating2;
//                }

//                System.out.println("！！！！！！！！！！ url："+url);
//                BackgroundTask backgroundTask = new BackgroundTask(this,url,payHandler);
//                backgroundTask.doInBackground();


                break;
            case R.id.iv1:
                addPhoto(11);
                break;
            case R.id.iv2:
                addPhoto(12);
                break;
            case R.id.iv3:
                addPhoto(13);
                break;

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

    private class PayHandler extends Handler {

        ProgressDialog progressDialog;

        public PayHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        if (!bundle.getString("result").equals("null")) {
                            try {
//                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                Toast.makeText(AfterSaleActivity2.this, jsonObject.optString("msg").toString(), Toast.LENGTH_SHORT).show();
                                if (jsonObject.optBoolean("flag")) {
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(AfterSaleActivity2.this, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(AfterSaleActivity2.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public void addPhoto(final int i) {
        final AlertDialog alertDialogChangeUserPicture2 = new AlertDialog.Builder(AfterSaleActivity2.this).create();
        alertDialogChangeUserPicture2.show();
        final Window alertWindow2 = alertDialogChangeUserPicture2.getWindow();
        alertWindow2.setContentView(R.layout.alert_change_user_picture_layout);//对话框布局
        alertWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_white_button_background));//样式文件
        LinearLayout changeByAlbum2 = (LinearLayout) alertWindow2.findViewById(R.id.change_user_picture_by_album);//相机按钮
        LinearLayout changeByCamera2 = (LinearLayout) alertWindow2.findViewById(R.id.change_user_picture_by_camera);//相册按钮
        changeByCamera2.setVisibility(View.GONE);
        changeByAlbum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                        /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                        /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                         /* 取得相片后返回本画面 */
                startActivityForResult(intent, i);
                alertDialogChangeUserPicture2.cancel();
            }
        });
        changeByCamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSdcardExisting()) {//判断是否有sd卡
                    Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
//                                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    startActivityForResult(cameraIntent, i);
                } else {
                    Toast.makeText(v.getContext(), "请插入sd卡", Toast.LENGTH_LONG)
                            .show();
                }
                alertDialogChangeUserPicture2.cancel();
            }
        });
    }

    private void uploadImage(String filePath) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("文件上传中...");
        progressDialog.show();
        String url = API.UPLOAD_IMAGE;
        org.xutils.http.RequestParams requestParams = new org.xutils.http.RequestParams(url);
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", new File(filePath));
        UploadHandler uploadHandler = new UploadHandler(progressDialog);
        xTask xTask = new xTask(this, uploadHandler, url, requestParams);
        xTask.doing();
    }

    public String arrS = "";

    private class UploadHandler extends Handler {

        ProgressDialog progressDialog;

        public UploadHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case xTask.SUCCESS: {
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        String path = bundle.getString("result");
                        arrS += path + ",";


                    }
                }
                break;
                case xTask.ERROR: {
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        Toast.makeText(AfterSaleActivity2.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                case xTask.CANCELLED: {
                    progressDialog.dismiss();
                }
                break;
                case xTask.FINISHED: {
                    progressDialog.dismiss();
                }
                break;
            }
        }
    }


}
