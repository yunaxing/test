package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuna on 2017/8/20.
 */

public class ZhangDanDetailActivity extends Activity{

    private LinearLayout imgBack;
    private EditText editZhifubaoZhanghao;
    private EditText editName;
    private EditText editTiXianJinE;
    private Button btnConfirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_qianbao_tixian);

        initView();
    }
    private void initView(){
        editTiXianJinE = (EditText)this.findViewById(R.id.zhi_fubao_jine);
        editName = (EditText)this.findViewById(R.id.zhi_fubao_name);
        editZhifubaoZhanghao = (EditText)this.findViewById(R.id.zhi_fubao_zhanghao);
        btnConfirm = (Button)this.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTiXianJinE.getText().toString().isEmpty()){
                    Toast.makeText(getApplication(),"提现金额不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.parseInt(editTiXianJinE.getText().toString())<20){
                    Toast.makeText(getApplication(),"最低提现金额为20元",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editName.getText().toString().isEmpty()){
                    Toast.makeText(getApplication(),"收款人姓名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editZhifubaoZhanghao.getText().toString().isEmpty()){
                    Toast.makeText(getApplication(),"支付宝账号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                TiXian();
            }
        });
        imgBack = (LinearLayout) this.findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void TiXian(){
        LOD lod = new LOD(this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.show();

        TiXian getMoney = new TiXian(progressDialog);
        String url = API.TIXIAN+"&uid="+lod.get("USER_INFO","UID","")+"&amount="+editTiXianJinE.getText().toString()
                +"&remark="+editName.getText().toString()+editZhifubaoZhanghao.getText().toString()
                + "&access_token="+(new Sha1().getDigestOfString((editTiXianJinE.getText().toString()+ DateUtil.getCurrentDate()+editName.getText().toString()+editZhifubaoZhanghao.getText().toString()
                +lod.get("USER_INFO","UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());;
        BackgroundTask backgroundTask = new BackgroundTask(getApplication(),url,getMoney);
        backgroundTask.doInBackground();
    }

    private class TiXian extends Handler {

        ProgressDialog progressDialog;

        public TiXian(ProgressDialog progressDialog) {
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
//                            tvYUE.setText(jsonObject.getString("user_money"));
//                            tvKaixinDou.setText(jsonObject.getString("kaixindou"));
                            if(jsonObject.getString("flag").equals("true")){
                                Toast.makeText(getApplication(),"操作成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplication(),"操作失败",Toast.LENGTH_SHORT).show();
                            }

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
