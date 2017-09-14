package com.qkxmall.mall.views.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.reg.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private ImageView backup = null;
    private EditText name = null;
    private EditText password = null;
    private Button login = null;
    private TextView register = null;
    private TextView forget = null;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        context = LoginActivity.this;

        backup.setOnClickListener(new OnClick());
        register.setOnClickListener(new OnClick());
        forget.setOnClickListener(new OnClick());
        login.setOnClickListener(new OnClick());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.login_backup);
        name = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        forget = (TextView) findViewById(R.id.forgetPassword);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_backup:
                    LoginActivity.this.finish();
                    break;
                case R.id.login:
                    if (notNull(LoginActivity.this)){
                        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("登陆中...");
                        progressDialog.show();
                        LoginHandler loginHandler = new LoginHandler(progressDialog);
                        String loginUrl = API.USER_LOGIN+"&phone="+name.getText().toString().trim()+"&pwd="+password.getText().toString().trim();
                        BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this,loginUrl,loginHandler);
                        backgroundTask.doInBackground();
                    }
                    break;
                case R.id.register:
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                    break;
                case R.id.forgetPassword:
                    startActivity(new Intent(LoginActivity.this,ChangePasswordActivity.class));
                    break;
            }
        }
    }
//添加代码部分
    //private boolean FristEnter(){

      //  getSharedPreferences("isFristEnter",Context.MODE_PRIVATE).edit().putBoolean("isFristEnter",true);

        //return false;
    //}



    private boolean notNull(Context context){
        if (name.getText().toString().trim().equals("")){
            Toast.makeText(context,"请输入用户名",Toast.LENGTH_SHORT).show();
        }else {
            if (password.getText().toString().trim().equals("")){
                Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            }else {
                return true;
            }
        }
        return false;
    }

    private class LoginHandler extends Handler{
        ProgressDialog progressDialog;

        public LoginHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle  != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0 ;i< jsonArray.length() ;i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("isLogin",true);
                                    //添加代码部分设置第一次登陆有向导界面
                                    editor.putBoolean("isFristEnter",true).commit();

                                    editor.putString("UID", object.getString("id"));
                                    editor.commit();
                                    LoginActivity.this.finish();
                                }
                                Toast.makeText(LoginActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登陆失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


}
