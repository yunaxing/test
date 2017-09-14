package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.func.coucou.adapter.CouCouHistoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.qkxmall.mall.R.id.tv_name;

/**
 * Created by yuna on 2017/8/8.
 */

public class CouCouRewardActivity extends Activity {

    private ListView listView;
    private TextView tv_name, tv_participate_time, tv_start, tv_start_ip, tv_jiexiao, tv_jiexiao_ip,
            tv_reward_name, tv_reward_no,
            tv_total, tv_renci, tv_shang, tv_yu, tv_lucky_base, tv_lucky_num;
    private ImageView img_touxiang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_coucou_reward_list);
        initView();
        getCouCouList();
    }

    private void initView(){
        listView = (ListView)this.findViewById(R.id.listview);
        tv_name = (TextView)this.findViewById(R.id.tv_name);
        img_touxiang = (ImageView)this.findViewById(R.id.img_touxiang);
        tv_participate_time = (TextView)this.findViewById(R.id.tv_participate_time);
        tv_start = (TextView)this.findViewById(R.id.tv_start);
        tv_start_ip = (TextView)this.findViewById(R.id.tv_start_ip);
        tv_jiexiao = (TextView)this.findViewById(R.id.tv_jiexiao);
        tv_jiexiao_ip = (TextView)this.findViewById(R.id.tv_jiexiao_ip);
        tv_reward_name = (TextView)this.findViewById(R.id.tv_reward_name);
        tv_reward_no = (TextView)this.findViewById(R.id.tv_reward_no);
        tv_total = (TextView)this.findViewById(R.id.tv_total);
        tv_renci = (TextView)this.findViewById(R.id.tv_renci);
        tv_shang = (TextView)this.findViewById(R.id.tv_shang);
        tv_yu = (TextView)this.findViewById(R.id.tv_yu);
        tv_lucky_base = (TextView)this.findViewById(R.id.tv_lucky_base);
        tv_lucky_num = (TextView)this.findViewById(R.id.tv_lucky_num);
    }

    private void getCouCouList(){
        String address = API.COUCOU_LIST_REWARD+"&cid="+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());

        Log.e("Address", address);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("凑凑历史...");
        progressDialog.show();
        COUCOULIST loadData = new COUCOULIST(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(this, address, loadData);
        backgroundTask.doInBackground();
    }

    private class COUCOULIST extends Handler {
        ProgressDialog progressDialog;

        public COUCOULIST(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject objectList = new JSONObject(bundle.getString("result"));
                            JSONArray jsonArray =  objectList.getJSONArray("rows");
                            ArrayList<HistoryItem> itemList = new ArrayList<>();
                            for(int i=0;i<jsonArray.length(); i++){
                                HistoryItem item = new HistoryItem();
                                JSONObject object = jsonArray.getJSONObject(i);
                                item.setCreater(object.getString("create_user"));
                                item.setStatus(object.getInt("status"));
                                itemList.add(item);
                            }


                            CouCouHistoryAdapter adapter = new CouCouHistoryAdapter(getApplication(), itemList);
                            listView.setAdapter(adapter);

                            //Jiang
                            JSONObject jsonObjectJiang =  objectList.getJSONObject("jiang");
//                            img_touxiang.setImageURI();
                            tv_participate_time.setText(jsonObjectJiang.getString("pnum").toString());
                            tv_start.setText(jsonObjectJiang.getString("microtime").split(" ")[0]);
                            tv_start_ip.setText(jsonObjectJiang.getString("microtime").split(" ")[1]);
                            tv_jiexiao.setText(jsonObjectJiang.getString("jxdate").split(" ")[0]);
                            tv_jiexiao_ip.setText(jsonObjectJiang.getString("jxdate").split(" ")[1]);
                            tv_reward_name.setText(jsonObjectJiang.getString("nickname"));
                            tv_reward_no.setText(jsonObjectJiang.getString("code"));

                            //result
                            JSONObject jsonObjectResult =  objectList.getJSONObject("result");
                            tv_total.setText(jsonObjectResult.getString("total"));
                            tv_renci.setText(jsonObjectResult.getString("pnum"));
                            tv_shang.setText(jsonObjectResult.getString("shang"));
                            tv_yu.setText(jsonObjectResult.getString("yu"));
                            tv_lucky_base.setText(jsonObjectResult.getString("luckycode"));
                            //From Jiang
                            tv_lucky_num.setText(jsonObjectJiang.getString("code"));

                            Log.v("jsonarray==",objectList.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(getApplication(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
