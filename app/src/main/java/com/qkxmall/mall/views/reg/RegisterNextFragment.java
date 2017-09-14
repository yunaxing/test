package com.qkxmall.mall.views.reg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sunshine on 01/15/2016.
 */
public class RegisterNextFragment extends Fragment {
    Context context;
    private ImageView navigation = null;
    private EditText name = null;
    private EditText password = null;
    private EditText retype = null;
    private Button register = null;

    String phone = "";
    String code = "";


    public void newInstance(Context context){
        this.context = context;
    }

    public RegisterNextFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null){
            phone = getArguments().getString("phone");
            code = getArguments().getString("code");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_register_next,container,false);
        init(rootView);

        navigation.setOnClickListener(new OnClick());
        register.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        name = (EditText) rootView.findViewById(R.id.name);
        password = (EditText) rootView.findViewById(R.id.password);
        retype = (EditText) rootView.findViewById(R.id.retype);
        register = (Button) rootView.findViewById(R.id.register);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    getFragmentManager().popBackStack();
                }
                    break;
                case R.id.register:{
                    if (!name.getText().toString().trim().equals("")){
                        if (!password.getText().toString().trim().equals("")){
                            if (!retype.getText().toString().trim().equals("")){
                                if (password.getText().toString().trim().equals(retype.getText().toString().trim())){
                                    ProgressDialog progressDialog = new ProgressDialog(context);
                                    progressDialog.setMessage("注册中...");
                                    progressDialog.show();
                                    RegisterHandler registerHandler = new RegisterHandler(progressDialog);
                                    String url = API.USER_REGISTER+"&phone="+phone+"&pwd="+password.getText().toString().trim()+"&code="+code;
                                    BackgroundTask backgroundTask = new BackgroundTask(context,url,registerHandler);
                                    backgroundTask.doInBackground();
                                }else {
                                    Toast.makeText(context, "两次密码输入不符，请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context, "请再次输入密码", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "请输入昵称", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    private class RegisterHandler extends Handler{
        ProgressDialog progressDialog;

        public RegisterHandler(ProgressDialog progressDialog) {
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
                                UpdateUserInfo updateUserInfo = new UpdateUserInfo();
                                JSONObject object = jsonObject.getJSONObject("data");
                                String url = API.UPDATE_USER_INFO+"&field=nickname"
                                        +"&uid="+object.getString("id")+"&v="+name.getText().toString().trim();
                                BackgroundTask backgroundTask = new BackgroundTask(context,url,updateUserInfo);
                                backgroundTask.doInBackground();
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
                    Toast.makeText(context, "请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private class UpdateUserInfo extends Handler{
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
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    RegisterActivity.registerActivity.finish();
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

}
