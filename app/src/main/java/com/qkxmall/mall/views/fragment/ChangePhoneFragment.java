package com.qkxmall.mall.views.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class ChangePhoneFragment extends Fragment {
    Context context;

    private ImageView backup = null;
    private EditText password = null;
    private TextView account = null;
    private Button next = null;

    private EditText mCode;
    private Button mSend;

    private Timer timer = new Timer();


    public void init(Context context) {
        this.context = context;
    }

    public ChangePhoneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_phone, container, false);
        init(rootView);
        LOD lod = new LOD(context);
        String url = API.USER_INFO + "&uid=" + lod.get("USER_INFO", "UID", "");
        GetUserInformation getUserInformation = new GetUserInformation();
        BackgroundTask backgroundTask = new BackgroundTask(context, url, getUserInformation);
        backgroundTask.doInBackground();

        backup.setOnClickListener(new OnClick());
        next.setOnClickListener(new OnClick());
        mSend.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        backup = (ImageView) rootView.findViewById(R.id.backup);
        password = (EditText) rootView.findViewById(R.id.password);
        account = (TextView) rootView.findViewById(R.id.account);
        next = (Button) rootView.findViewById(R.id.next);
        mCode = (EditText) rootView.findViewById(R.id.my_check_number);
        mSend = (Button) rootView.findViewById(R.id.my_send);
    }

    private int time = 60;
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            new Runnable() {

                @Override
                public void run() {
                    time--;
                    mSend.setText(time + "秒后重发验证码");
                    if (time < 0) {
                        timer.cancel();
                        mSend.setClickable(true);
                        mSend.setText("发送验证码");
                    }
                }
            };
        }
    };

    private class GetSMSCodes extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")) {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast
                                        .LENGTH_SHORT).show();
                                mSend.setClickable(false);
                                timer.schedule(timerTask, 10, 1000);
                            } else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast
                                        .LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "发送失败，请重试", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

//                case R.id.my_send://发送验证码
//
//                    String uri = API.GET_SMS_CODE + "&phone=" + account.getText().toString()
//                            .trim();
//                    GetSMSCodes getSMSCode = new GetSMSCodes();
//                    BackgroundTask backgroundTask1 = new BackgroundTask(context, uri,
//                            getSMSCode);
//                    backgroundTask1.doInBackground();
//
//
//                    break;

                case R.id.backup: {

                    FragmentBlankActivity.fragmentBlankActivity.finish();
                }
                break;
                case R.id.next: {
                    //原密码正确 跳转
                    LOD lod = new LOD(context);
                    if (!account.getText().toString().trim().equals("")) {

                            if (!password.getText().toString().trim().equals("")) {
                                String url = API.USER_LOGIN + "&phone=" + account.getText()
                                        .toString().trim() + "&pwd=" + password.getText()
                                        .toString().trim();
                                CheckPassword checkPassword = new CheckPassword();
                                BackgroundTask backgroundTask = new BackgroundTask(context, url,
                                        checkPassword);
                                backgroundTask.doInBackground();
                            }

                    } else {
                        Toast.makeText(context, "网络连接较慢，请重试", Toast.LENGTH_SHORT).show();

                        String url = API.USER_INFO + "&uid=" + lod.get("USER_INFO", "UID", "");
                        GetUserInformation getUserInformation = new GetUserInformation();
                        BackgroundTask backgroundTask = new BackgroundTask(context, url,
                                getUserInformation);
                        backgroundTask.doInBackground();
                    }

                }
                break;
            }
        }
    }

    public void initDenfl(){

            LOD lod = new LOD(context);
            String address ="http://www.qkxmall.com/index.php?m=api&c=member&a=login&phone="+account.getText().toString()+"&pwd="+password.getText().toString().trim();
            doInBackground(address);

            if (!password.getText().toString().trim().equals("")&&bl==true) {
                String url = API.USER_LOGIN + "&phone=" + account.getText()
                        .toString().trim() + "&pwd=" + password.getText()
                        .toString().trim();
                CheckPassword checkPassword = new CheckPassword();
                BackgroundTask backgroundTask = new BackgroundTask(context, url,
                        checkPassword);
                backgroundTask.doInBackground();
            }



    }

    private class GetUserInformation extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            account.setText(jsonObject.getString("mobile_phone"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "网络中断，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    private class CheckCodeHandlers extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        String result = bundle.getString("result");
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getBoolean("flag")) {

                                //下面被注释掉的是本来的源码
                                // String bindAddress = API.UPDATE_USER_INFO+"&uid="+lod.get
                                // ("USER_INFO","UID","")+"&mobile_phone="+phone.getText()
                                // .toString().trim();这是原来的代码！！！！
                                // http://www.qkxmall.com/index
                                // .php?m=api&c=member&a=modifyuserinfo&field=mobile_phone&uid=28
                                // &v=13593754759这是测试时候用的地址和这里的代码没关系
                                //"http://www.qkxmall.com/index
                                // .php?m=api&c=member&a=modifyuserinfo";为了和上面的地址做比较
//                                String bindAddress = API.UPDATE_USER_INFO + "&field=mobile_phone"
//                                        + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
//                                        account.getText().toString().trim();
//                                String bindAddress=API.UPDATE_USER_INFO + "&field=mobile_phone"+"&field=username"
//                                        + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
//                                        phone.getText().toString().trim()+"&v"+phone.getText().toString().trim();

                                      System.out.println("验证成功++++++++++++++++++++++++++++++++++++++");
                            } else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast
                                        .LENGTH_SHORT).show();
                                System.out.println("验证失败1++++++++++++++++++++++++++++++++++++++");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            System.out.println("验证失败2++++++++++++++++++++++++++++++++++++++");

                        }
                    }

                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, " 网络连接失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    private void doInBackground(String uri){
        HttpUtils utils=new HttpUtils();
        utils.configCurrentHttpCacheExpiry(1000 * 0);
        // 设置超时时间
        utils.configTimeout(5 * 1000);
        utils.configSoTimeout(5 * 1000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, uri, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=responseInfo.result;
                getJson(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }
private boolean bl=false;
    private void getJson(String url){
        try {
            JSONObject jsonObject = new JSONObject(url);
            if(jsonObject.getBoolean("flag")){
                             bl=true;
            }else{
                Toast.makeText(context,"验证码有误",Toast.LENGTH_LONG).show();

            }

        }catch (JSONException e){

        }

    }
    private class CheckPassword extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")) {
                                ChangePhoneNextFragment changePhoneNextFragment = new
                                        ChangePhoneNextFragment();
                                changePhoneNextFragment.init(context);
                                getFragmentManager().beginTransaction().replace(R.id
                                        .fragment_blank, changePhoneNextFragment).commit();
                            } else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast
                                        .LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
