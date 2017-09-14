package com.qkxmall.mall.views.reg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.views.DocumentsActivity;
import com.qkxmall.mall.wxapi.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sunshine on 01/15/2016.
 */
public class RegisterFragment extends Fragment {
    private Context context;
    private ImageView navigation = null;
    private EditText phone = null;
    private EditText SMSCode = null;
    private Button sendSMS = null;
    private CheckBox accept = null;
    private TextView agreement = null;
    private Button next = null;

    private ImageView img;
    private EditText tve;
    private String android_id;

    int time = 60;



    public void newInstance(Context context){
        this.context = context;
    }

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register,container,false);
        init(rootView);

        android_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//        bitmap.clearCache();
//        bitmap.display(img, "http://www.qkxmall.com/index.php?m=api&c=member&a=getcode");
        initTXM();
//        Glide.with(getActivity()).load("http://www.qkxmall.com/index.php?m=api&c=member&a=getcode").diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        navigation.setOnClickListener(new OnClick());
        sendSMS.setOnClickListener(new OnClick());
        next.setOnClickListener(new OnClick());
        //以下点击事件后来加的
        agreement.setOnClickListener(new OnClick());
        return rootView;
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
                url );
        BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
        backgroundTask.doInBackground();

    }
    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        phone = (EditText) rootView.findViewById(R.id.phone);
        SMSCode = (EditText) rootView.findViewById(R.id.SMSCode);
        sendSMS = (Button) rootView.findViewById(R.id.sendSMS);
        accept = (CheckBox) rootView.findViewById(R.id.accept);
        agreement = (TextView) rootView.findViewById(R.id.agreement);
        next = (Button) rootView.findViewById(R.id.next);

        img= (ImageView) rootView.findViewById(R.id.tx_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTXM();
            }
        });
        tve= (EditText) rootView.findViewById(R.id.tx_et);
    }



    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    getActivity().finish();
                }
                    break;
                case R.id.agreement:{
                    startActivity(new Intent(context,DocumentsActivity.class));
                }
                    break;
                case R.id.sendSMS:{
                     if (tve.getText().toString().equals("")){
                         Toast.makeText(context, "请输先输入图形码", Toast.LENGTH_SHORT).show();
                         return;
                     }
                    if (!phone.getText().toString().trim().equals("")) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("发送验证码...");
                        progressDialog.show();
                        SendSMSHandler sendSMSHandler = new SendSMSHandler(progressDialog);
                        String url = "http://www.qkxmall.com/index.php?m=api&c=member&a=getvalidatecode"+ "&phone="+phone.getText().toString().trim()+"&codestr="+tve.getText().toString()+"&client=android"+"&imei="+android_id;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  url" +
                                url );
                        BackgroundTask backgroundTask = new BackgroundTask(context,url,sendSMSHandler);
                        backgroundTask.doInBackground();
                    }else {
                        Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
                case R.id.next:{
                    if (accept.isChecked()) {
                        if (notNull(context)) {
                            RegisterNextFragment registerNextFragment = new RegisterNextFragment();
                            registerNextFragment.newInstance(context);
                            Bundle bundle = new Bundle();
                            bundle.putString("phone", phone.getText().toString().trim());
                            bundle.putString("code", SMSCode.getText().toString().trim());
                            registerNextFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().addToBackStack("register").add(R.id.registerRoot, registerNextFragment).commit();
                        }
                    }else {
                        Toast.makeText(context, "请先同意服务条款", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    private boolean notNull(Context context){
        if (phone.getText().toString().trim().equals("")){
            Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
        }else {
            if (SMSCode.getText().toString().trim().equals("")){
                Toast.makeText(context,"请输入短信验证码",Toast.LENGTH_SHORT).show();
            }else {
                return true;
            }
        }
        return false;
    }

    private class SendSMSHandler extends Handler{
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
                                final Timer timer = new Timer();
                                final Handler handler = new Handler(){
                                    @Override
                                    public void handleMessage(Message msg) {
                                        if (time == 0){
                                            timer.cancel();
                                            sendSMS.setText("发送验证码");
                                            sendSMS.setClickable(true);
                                            time = 60;
                                        }else {
                                            sendSMS.setClickable(false);
                                            sendSMS.setText(msg.arg1+"秒后重发");
                                        }
                                    }
                                };
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
