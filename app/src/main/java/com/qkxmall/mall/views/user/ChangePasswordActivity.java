package com.qkxmall.mall.views.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.wxapi.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView backup = null;
    private EditText phone = null;
    private EditText check = null;
    private Button get = null;
    private Button next = null;
    private EditText type = null;
    private EditText retype = null;
    private Button finish = null;
    private Context context;
    private int time = 60;
    private Timer timer = null;

    String phoneNumber;

    private ImageView img;
    private EditText tve;
    private String android_id;
    private EditText xinMima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        initTXM();
        context = this;
        backup.setOnClickListener(new OnClickListeners());
        next.setOnClickListener(new OnClickListeners());
        get.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.reset_backup);
        phone = (EditText) findViewById(R.id.reset_phone);
        check = (EditText) findViewById(R.id.reset_check);
        get = (Button) findViewById(R.id.reset_get);
        next = (Button) findViewById(R.id.reset_next);
        type = (EditText) findViewById(R.id.reset_type);
        retype = (EditText) findViewById(R.id.reset_retype);
        finish = (Button) findViewById(R.id.reset_finish);

        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        img= (ImageView) findViewById(R.id.tx_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTXM();
            }
        });
        tve= (EditText) findViewById(R.id.tx_et);
        xinMima= (EditText) findViewById(R.id.xin_mima);

    }

    private String code;
    public void initTXM(){
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){

                    case BackgroundTask.SUCCESS:{

                        Bundle bundle = msg.getData();
                        if (bundle != null){
                            try {
                                JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                code=jsonObject.optString("code");
                                System.out.println("！！！！！！！！！！！拿到的验证码："+code);
                                img.setImageBitmap(CodeUtils.getInstance().createBitmap(code));
//                                  bitmap.display(img, String.valueOf(CodeUtils.getInstance().createBitmap(code)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                    case BackgroundTask.FAILURE:{

                        Toast.makeText(context, "发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }

        };

        String url = "http://www.qkxmall.com/index.php?m=api&c=member&a=getcode&imei="+android_id;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  TXM" +
                url);
        BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
        backgroundTask.doInBackground();

    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.reset_backup: {
                    ChangePasswordActivity.this.finish();
                }
                    break;
                case R.id.reset_get:
                {
                    if (tve.getText().toString().equals("")){
                        Toast.makeText(context, "请输入图形码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!phone.getText().toString().trim().equals("")) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("发送验证码...");
                        progressDialog.show();
                        SendSMSHandler sendSMSHandler = new SendSMSHandler(progressDialog);

                        String url = API.GET_SMS_CODE + "&codestr="+tve.getText().toString()+"&phone="+phone.getText().toString().trim()+"&client=android&imei="+android_id;
                        System.out.println("!!!!!!!!!!!!!!!!!!!! +"+url);
                        BackgroundTask backgroundTask = new BackgroundTask(context,url,sendSMSHandler);
                        backgroundTask.doInBackground();
                    }else {
                        Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
                case R.id.reset_next:{
                        if (xinMima.getText().toString().trim().equals("")){
                            Toast.makeText(context, "请输入新密码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    if (!phone.getText().toString().trim().equals("")){
                        if (!check.getText().toString().trim().equals("")){
                                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! 下一步");
                            String checkSMS = API.CHECK_SMS+"&phone="+phone.getText().toString().trim()+"&code="+check.getText().toString().trim();//前段验证码是否正确
                            Handler handler = new Handler(){
                                @Override
                                public void handleMessage(Message msg) {
                                    switch (msg.what){
                                        case BackgroundTask.SUCCESS:{
                                            Bundle bundle = msg.getData();
                                            if (bundle != null){
                                                try {
                                                    JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                                    if ( jsonObject.getBoolean("flag")){
                                                        xiuGaiMIma();
//                                                        //清除timer
//                                                        timer.cancel();
//                                                        time = -1;
//                                                        setContentView(R.layout.activity_change_password_next);
//                                                        init();
//                                                        backup.setOnClickListener(new OnClickListeners());
//                                                        finish.setOnClickListener(new OnClickListeners());

                                                    }else {
                                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                            break;
                                        case BackgroundTask.FAILURE:{

                                        }
                                            break;
                                    }
                                }
                            };

                            phoneNumber = phone.getText().toString().trim();
                            BackgroundTask backgroundTask = new BackgroundTask(context,checkSMS,handler);
                            backgroundTask.doInBackground();

                        }else {
                            Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
                case R.id.reset_finish: {
                    if (!type.getText().toString().trim().equals("")){
                        if (!retype.getText().toString().trim().equals("")){
                            if (type.getText().toString().trim().equals(retype.getText().toString().trim())){
                                // TODO: 02/17/2016
                                String resetPwdUrl = API.RESET_PASSWORD+"&phone="+phoneNumber+"&newpwd="+type.getText().toString().trim();
                                Handler handler = new Handler(){
                                    @Override
                                    public void handleMessage(Message msg) {
                                        switch (msg.what){
                                            case BackgroundTask.SUCCESS:{
                                                Bundle bundle = msg.getData();
                                                if (bundle != null){
                                                    String result = bundle.getString("result");
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(result);
                                                        if (jsonObject.getBoolean("flag")){
                                                            Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                                                            ChangePasswordActivity.this.finish();
                                                            startActivity(new Intent(ChangePasswordActivity.this,AccountManageActivity.class));
                                                        }else {
                                                            Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                        Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                                break;
                                            case BackgroundTask.FAILURE:{

                                            }
                                                break;
                                        }
                                    }
                                };
                                BackgroundTask backgroundTask = new BackgroundTask(context,resetPwdUrl,handler);
                                backgroundTask.doInBackground();

                            }else {
                                Toast.makeText(context, "两次密码输入不符，请重新输入", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "请输入确认密码", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "请输入新密码", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    public void xiuGaiMIma(){
        String resetPwdUrl = API.RESET_PASSWORD+"&phone="+phoneNumber+"&newpwd="+xinMima.getText().toString().trim();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case BackgroundTask.SUCCESS:{
                        Bundle bundle = msg.getData();
                        if (bundle != null){
                            String result = bundle.getString("result");
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                if (jsonObject.getBoolean("flag")){
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                       finish();
                                }else {
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                    case BackgroundTask.FAILURE:{

                    }
                    break;
                }
            }
        };
        BackgroundTask backgroundTask = new BackgroundTask(context,resetPwdUrl,handler);
        backgroundTask.doInBackground();
    }

    private class TimerHandler extends  Handler{
        @Override
        public void handleMessage(Message msg) {
            if (time >= 0) {
                if (time == 0) {
                    timer.cancel();
                    get.setText("发送验证码");
                    get.setClickable(true);
                    time = 60;
                } else {
                    get.setClickable(false);
                    get.setText(msg.arg1 + "秒后重发");
                }
            }
        }
    }

    /**
     * 发送验证码
     */
    private class SendSMSHandler extends Handler {
        ProgressDialog progressDialog;

        public SendSMSHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                timer = new Timer();
                                time = 60;
                                final Handler handler = new TimerHandler();
                                final TimerTask timerTask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        Message message = new Message();
                                        time--;
                                        message.arg1 = time;
                                        handler.sendMessage(message);
                                    }
                                };
                                timer.schedule(timerTask,100,1000);
                            }else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
