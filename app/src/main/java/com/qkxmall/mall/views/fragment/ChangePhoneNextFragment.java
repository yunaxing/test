package com.qkxmall.mall.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.wxapi.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class ChangePhoneNextFragment extends Fragment {
    Context context;
    int time = 60;
    Timer timer = new Timer();

    private ImageView backup = null;
    private Button sendNumber = null;
    private EditText phone = null;
    private EditText check = null;
    private Button next = null;

    private ImageView img;
    private EditText tve;
    private String android_id;

    public void init(Context context) {
        this.context = context;
    }

    public ChangePhoneNextFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_phone_next, container, false);
        init(rootView);
        initTXM();
        backup.setOnClickListener(new OnClick());
        next.setOnClickListener(new OnClick());
        sendNumber.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        backup = (ImageView) rootView.findViewById(R.id.backup);
        next = (Button) rootView.findViewById(R.id.next);
        sendNumber = (Button) rootView.findViewById(R.id.send);
        phone = (EditText) rootView.findViewById(R.id.phone);
        check = (EditText) rootView.findViewById(R.id.check_number);

        img= (ImageView) rootView.findViewById(R.id.tx_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTXM();
            }
        });
        tve= (EditText) rootView.findViewById(R.id.tx_et);


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
    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backup:
//                    FragmentManager backupF = getFragmentManager();
//                    backupF.popBackStack();
                    getActivity().finish();
                    break;
                case R.id.send:
                    if (tve.getText().toString().equals("")){
                        Toast.makeText(context, "请输入图形码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!phone.getText().toString().trim().equals("")) {
                        String url = API.GET_SMS_CODE + "&codestr="+tve.getText().toString()+"&phone="+phone.getText().toString().trim()+"&client=android&imei="+android_id;
//                        String url = API.GET_SMS_CODE + "&phone=" + phone.getText().toString().trim()+"&client=android&imei="+android_id;
                        GetSMSCode getSMSCode = new GetSMSCode();
                        BackgroundTask backgroundTask = new BackgroundTask(context, url,
                                getSMSCode);
                        backgroundTask.doInBackground();
                    }
                    break;
                case R.id.next:
//                       SharedPreferences sp=context.getSharedPreferences("USER_INFO",0);
//                    System.out.println("+++++++++++++++++++++++++++++++&uid=" +sp.getString("UID",""));

                    if (!check.getText().toString().trim().equals("")) {
                        LOD lod = new LOD(context);
                        String address = API.CHECK_SMS + "&uid=" + lod.get("USER_INFO", "UID",
                                "") + "&phone=" + phone.getText().toString().trim() + "&code=" +
                                check.getText().toString().trim();
                        CheckCodeHandler handler = new CheckCodeHandler();
                        BackgroundTask backgroundTask = new BackgroundTask(context, address,
                                handler);
                        backgroundTask.doInBackground();

                    }
                    break;
            }
        }
    }

    public void RefreshUsername(Bundle bundle){


            if (bundle != null) {
                String result = bundle.getString("result");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getBoolean("flag")) {
                        LOD lod = new LOD(context);
                        //下面被注释掉的是本来的源码
                        // String bindAddress = API.UPDATE_USER_INFO+"&uid="+lod.get
                        // ("USER_INFO","UID","")+"&mobile_phone="+phone.getText()
                        // .toString().trim();这是原来的代码！！！！
                        // http://www.qkxmall.com/index
                        // .php?m=api&c=member&a=modifyuserinfo&field=mobile_phone&uid=28
                        // &v=13593754759这是测试时候用的地址和这里的代码没关系
                        //"http://www.qkxmall.com/index
                        // .php?m=api&c=member&a=modifyuserinfo";为了和上面的地址做比较
//                        String bindAddress = API.UPDATE_USER_INFO + "&field=mobile_phone"
//                                + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
//                                phone.getText().toString().trim();
                                String bindAddress=API.UPDATE_USER_INFO +"&field=username"
                                        + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
                                        phone.getText().toString().trim();
                        Handler handler1 = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what) {
                                    case BackgroundTask.SUCCESS: {
                                        Bundle bundle1 = msg.getData();
                                        if (bundle1 != null) {
                                            String result = bundle1.getString("result");
                                            try {
                                                JSONObject jsonObject1 = new JSONObject
                                                        (result);
                                                if (jsonObject1.getBoolean("flag")) {
                                                    Toast.makeText(context, jsonObject1
                                                            .getString("msg"), Toast
                                                            .LENGTH_SHORT).show();
                                                    FragmentBlankActivity
                                                            .fragmentBlankActivity.finish();
                                                } else {
                                                    Toast.makeText(context, jsonObject1
                                                            .getString("msg"), Toast
                                                            .LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(context, "数据解析失败", Toast
                                                        .LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    break;
                                    case BackgroundTask.FAILURE: {
                                        Toast.makeText(context, "网络连接失败，请稍候再试", Toast
                                                .LENGTH_SHORT).show();
                                    }
                                    break;
                                }
                            }
                        };
                        BackgroundTask backgroundTask1 = new BackgroundTask(context,
                                bindAddress, handler1);
                        backgroundTask1.doInBackground();
                    } else {
                        Toast.makeText(context, jsonObject.getString("msg"), Toast
                                .LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                }
            }


    }

    private class CheckCodeHandler extends Handler {

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
                                LOD lod = new LOD(context);
                                //下面被注释掉的是本来的源码
                                // String bindAddress = API.UPDATE_USER_INFO+"&uid="+lod.get
                                // ("USER_INFO","UID","")+"&mobile_phone="+phone.getText()
                                // .toString().trim();这是原来的代码！！！！
                                // http://www.qkxmall.com/index
                                // .php?m=api&c=member&a=modifyuserinfo&field=mobile_phone&uid=28
                                // &v=13593754759这是测试时候用的地址和这里的代码没关系
                                //"http://www.qkxmall.com/index
                                // .php?m=api&c=member&a=modifyuserinfo";为了和上面的地址做比较
                                String bindAddress = API.UPDATE_USER_INFO + "&field=mobile_phone"
                                        + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
                                        phone.getText().toString().trim();
//                                String bindAddress=API.UPDATE_USER_INFO + "&field=mobile_phone"+"&field=username"
//                                        + "&uid=" + lod.get("USER_INFO", "UID", "") + "&v=" +
//                                        phone.getText().toString().trim()+"&v"+phone.getText().toString().trim();
                                Handler handler1 = new Handler() {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        switch (msg.what) {
                                            case BackgroundTask.SUCCESS: {
                                                Bundle bundle1 = msg.getData();
                                                if (bundle1 != null) {
                                                    String result = bundle1.getString("result");
                                                    try {
                                                        JSONObject jsonObject1 = new JSONObject
                                                                (result);
                                                        if (jsonObject1.getBoolean("flag")) {
                                                            Toast.makeText(context, jsonObject1
                                                                    .getString("msg"), Toast
                                                                    .LENGTH_SHORT).show();
                                                            //关闭当前界面
//                                                            FragmentBlankActivity
//                                                                    .fragmentBlankActivity.finish();
                                                        } else {
                                                            Toast.makeText(context, jsonObject1
                                                                    .getString("msg"), Toast
                                                                    .LENGTH_SHORT).show();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                        Toast.makeText(context, "数据解析失败", Toast
                                                                .LENGTH_SHORT).show();
                                                    }
                                                }
                                                //修改用户名
                                                RefreshUsername(bundle1);
                                            }
                                            break;
                                            case BackgroundTask.FAILURE: {
                                                Toast.makeText(context, "网络连接失败，请稍候再试", Toast
                                                        .LENGTH_SHORT).show();
                                            }
                                            break;
                                        }
                                    }
                                };
                                BackgroundTask backgroundTask1 = new BackgroundTask(context,
                                        bindAddress, handler1);
                                backgroundTask1.doInBackground();
                            } else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast
                                        .LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
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

    private class GetSMSCode extends Handler {

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
                                sendNumber.setClickable(false);
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

    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            new Runnable() {

                @Override
                public void run() {
                    time--;
                    sendNumber.setText(time + "秒后重发验证码");
                    if (time < 0) {
                        timer.cancel();
                        sendNumber.setClickable(true);
                        sendNumber.setText("发送验证码");
                    }
                }
            };
        }
    };


}
