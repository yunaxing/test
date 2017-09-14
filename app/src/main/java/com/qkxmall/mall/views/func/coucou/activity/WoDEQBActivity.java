package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.user.AccountRechargeActivity;
import com.qkxmall.mall.views.user.PerCenterMyWalletActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuna on 2017/8/20.
 */

public class WoDEQBActivity extends Activity{

    private TextView tvTiXian;
    private TextView tvDetail;
    private TextView tvKaixinDou;
    private TextView tvYUE;
    private LinearLayout imgBack;
    private RelativeLayout rlKaixinDou;
    private RelativeLayout rlChongzhi;
    private RelativeLayout rlTixian;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_qianbao);
        initView();
        initData();
    }

    private void initData(){
        LOD lod = new LOD(this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.show();

        GetMoney getMoney = new GetMoney(progressDialog);
        String url = API.USER_INFO+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(getApplication(),url,getMoney);
        backgroundTask.doInBackground();
    }
    private void initView(){
        tvDetail = (TextView)this.findViewById(R.id.tv_zhangdan_detail);
        tvTiXian = (TextView)this.findViewById(R.id.ti_xian);
        tvKaixinDou = (TextView)this.findViewById(R.id.tv_kaixindou);
        tvYUE = (TextView)this.findViewById(R.id.tv_remaining);
        imgBack = (LinearLayout) this.findViewById(R.id.img_back);
        rlKaixinDou = (RelativeLayout)this.findViewById(R.id.rl_kaixindou);
        rlChongzhi = (RelativeLayout)this.findViewById(R.id.rl_chongzhi);
        rlTixian = (RelativeLayout)this.findViewById(R.id.rl_tixian);
        rlChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),AccountRechargeActivity.class));
            }
        });
        rlKaixinDou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), QianBaoKaiXinDouPageActivity.class));
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),TiXianActivity.class));
            }
        });
        rlTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),ZhangDanDetailActivity.class));
            }
        });
    }
    private class GetMoney extends Handler {

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
                            tvYUE.setText(jsonObject.getString("user_money"));
                            tvKaixinDou.setText(jsonObject.getString("kaixindou"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
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
