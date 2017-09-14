package com.qkxmall.mall.views.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.func.webview.GetMethodActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PerCenterMyWalletActivity extends Activity {
    Context context;
    private ImageView backup = null;
    private TextView myRemaining = null;
    private TextView myHappyBean = null;
    private Button recharge = null;
    private Button getWay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_center_my_wallet);
        context = PerCenterMyWalletActivity.this;
        init();
        LOD lod = new LOD(context);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("");
        progressDialog.show();

        GetMoney getMoney = new GetMoney(progressDialog);
        String url = API.USER_INFO+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(context,url,getMoney);
        backgroundTask.doInBackground();

        backup.setOnClickListener(new OnClick());
        recharge.setOnClickListener(new OnClick());
        getWay.setOnClickListener(new OnClick());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.per_center_my_wallet_backup);
        recharge = (Button) findViewById(R.id.per_center_my_wallet_recharge);
        getWay = (Button) findViewById(R.id.per_center_my_wallet_get_way);
        myHappyBean = (TextView) findViewById(R.id.per_center_my_wallet_happy_bean);
        myRemaining = (TextView) findViewById(R.id.per_center_my_wallet_remaining);
    }

//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(PerCenterMyWalletActivity.this, FragmentMainActivity.class));
//        PerCenterMyWalletActivity.this.finish();
//    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.per_center_my_wallet_backup:
//                    startActivity(new Intent(PerCenterMyWalletActivity.this, FragmentMainActivity.class));
                    PerCenterMyWalletActivity.this.finish();
                    break;
                case R.id.per_center_my_wallet_recharge:
                    startActivity(new Intent(PerCenterMyWalletActivity.this,AccountRechargeActivity.class));
                    finish();
                    break;
                case R.id.per_center_my_wallet_get_way:
                    startActivity(new Intent(PerCenterMyWalletActivity.this,GetMethodActivity.class));
                    break;
            }
        }
    }

    private class GetMoney extends Handler{

        ProgressDialog progressDialog;

        public GetMoney(ProgressDialog progressDialog) {
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
                            myRemaining.setText(jsonObject.getString("user_money"));
                            myHappyBean.setText(jsonObject.getString("kaixindou"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
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

}
