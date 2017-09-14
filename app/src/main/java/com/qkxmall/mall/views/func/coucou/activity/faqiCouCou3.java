package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.func.coucou.adapter.CouCouGuiGeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuna on 2017/8/5.
 */

public class faqiCouCou3 extends Activity {
    String GID;
    String listImg;
    public  String mJnos;
       Bundle bundle;
       JSONArray jsonArray;
    private TextView tvName;
    int parentPosition = 0;
    int childPosition = 0;
    private ImageView imgConfrim;
     CouCouGuiGeAdapter guiGeAdapter;
    private SimpleDraweeView img;
    //默认position
    String[] positions;
    private ListView guiGe;
    private ImageView imgClose;
    public  List<HashMap<String, Object>> dataList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_choose);
        GID = getIntent().getStringExtra("id");
        listImg = getIntent().getStringExtra("imgs");
        initView();
        initData();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);
    }

    private void initView(){
        tvName = (TextView)findViewById(R.id.tv_name);
        guiGe = (ListView)findViewById(R.id.specList);
        img = (SimpleDraweeView)findViewById(R.id.image_title);
        imgConfrim = (ImageView)findViewById(R.id.img_confirm);
        imgClose =(ImageView)findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),faqiCouCou4.class).putExtra("imgs",getIntent().getStringExtra("imgs")).putExtra("id", getIntent().getStringExtra("id"))
                        .putExtra("create_pay", getIntent().getStringExtra("create_pay"))
                        .putExtra("cid", getIntent().getStringExtra("cid"))
                        .putExtra("name", tvName.getText().toString())
                        .putExtra("price",getIntent().getStringExtra("price")));

                finish();
            }
        });
    }

    private void initData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("获取商品规格中...");
        progressDialog.show();
        GetDetail getDetail = new GetDetail(progressDialog);
        String url = API.HUI_PRODUCTS_DETAIL + "&gid=" + GID;
        System.out.println("========GID" + GID);
        BackgroundTask backgroundTask = new BackgroundTask(this, url, getDetail);
        backgroundTask.doInBackground();
        String[] imageArray = listImg.split(",");
        img.setImageURI(Uri.parse(API.ADD+imageArray[0]));
    }
    private class GetDetail extends Handler {
        ProgressDialog progressDialog;

        public GetDetail(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    bundle = msg.getData();
                    if (bundle != null) {
                        try {

                            /*
                            * 显示商品规格
                            *3
                            * */
                            jsonArray = new JSONArray(bundle.getString("result"));
                            mJnos=bundle.getString("result");

                            Log.e("JSONResult", bundle.getString("result"));
                            System.out.println("wwwwwwwwwwwwwwwwwwwww 最初Json数据：" + bundle
                                    .getString("result"));
                            for (int m = 0; m < jsonArray.length(); m++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(m);
                                tvName.setText(jsonObject.getString("name"));
                                JSONObject spec_array = jsonObject.getJSONObject("spec_array");
                                List<HashMap<String, Object>> specList = new ArrayList<>();
                                //JSONObject ob = spec_array.getJSONObject("2");
//                                Log.e("ob",ob.toString());
                                for (int i = 0; i < spec_array.length(); i++) {
                                    for (int index = 0; index < 100; index++) {
//                                        Log.e("index", String.valueOf(index));
                                        if (spec_array.has(String.valueOf(index))) {
                                            JSONObject object = spec_array.getJSONObject(String
                                                    .valueOf(index));
//                                            Log.e("object",object.toString());
                                            HashMap<String, Object> data = new HashMap<>();
                                            data.put("id", object.get("id"));
                                            data.put("name", object.get("name"));

                                            data.put("value", object.get("value"));

                                            specList.add(data);
                                        }
                                        if (specList.size() == spec_array.length())
                                            break;
                                    }
                                }
                                System.out.println("wwwwwwwwwwwwwwwwwwwww  转化前数据源格式：" + specList
                                        .toString());
                                for (int i = 0; i < specList.size(); i++) {
                                    HashMap<String, Object> map = specList.get(i);
                                    HashMap<String, Object> dataMap = new HashMap<>();
                                    dataMap.put("title", map.get("name"));
                                    dataMap.put("id", map.get(i));
                                    String[] value = ((String) map.get("value")).split(",");
                                    System.out.println("wwwwwwwwwwwwwwwwwwwww  拼接的字符数组长度：" +
                                            value.length);
                                    List<HashMap<String, Object>> valueList = new ArrayList<>();
                                    for (int n = 0; n < value.length; n++) {
                                        HashMap<String, Object> hashMap = new HashMap<>();

                                        hashMap.put("item", value[n]);


                                        System.out.println("wwwwwwwwwwwwwwwwwwwww 每次HashMap添加的内容" +
                                                hashMap.toString());
                                        valueList.add(hashMap);
                                    }
                                    dataMap.put("guige", valueList);
                                    System.out.println("wwwwwwwwwwwwwwwwwwwww  dataList第" + i +
                                            "次赋值为：" + dataMap.toString());
                                    dataList.add(dataMap);
                                }

                                /*
                                * 父级下的子级Grid选中监听
                                * 传回内容为父级position
                                *
                                * */
                                List<Handler> parentHandlerList = new ArrayList<>();
                                for (int i = 0; i < dataList.size(); i++) {
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                            parentPosition = msg.arg1;
                                        }
                                    };
                                    parentHandlerList.add(handler);
                                }
                                //每个父级ListView下的子级Grid额选中状态
                                final List<List<Boolean>> isSelect = new ArrayList<>();
                                for (int i = 0; i < dataList.size(); i++) {
                                    List<Boolean> gSelect = new ArrayList<>();
                                    for (int a = 0; a < ((List<HashMap<String, Object>>) dataList
                                            .get(i).get("guige")).size(); a++) {
                                        if (a == 0) {
                                            gSelect.add(true);
                                        } else {
                                            gSelect.add(false);
                                        }
                                    }
                                    isSelect.add(gSelect);
                                }
                                /*
                                *
                                * */
                                /*
                                * 默认position
                                * 同时初始化参数显示
                                * */
                                positions = new String[dataList.size()];
                                for (int i = 0; i < positions.length; i++) {
                                    positions[i] = String.valueOf(0);
                                }

                                /*
                                * 子级操作监听Handle
                                * 传回内容为子级的position
                                *
                                * */
                                List<Handler> childHandlerList = new ArrayList<>();
                                for (int i = 0; i < dataList.size(); i++) {
                                    Handler handler = new Handler() {

                                        @Override
                                        public void handleMessage(Message msg) {
                                            childPosition = msg.arg1;
                                            Log.e("ParentsPosition", String.valueOf
                                                    (parentPosition));
                                            Log.e("ChildPosition", String.valueOf(childPosition));
                                            List<Boolean> select = new ArrayList<>();

                                            for (int a = 0; a < isSelect.get(parentPosition).size
                                                    (); a++) {

                                                if (a == childPosition) {
                                                    select.add(true);
                                                } else {
                                                    select.add(false);
                                                }
                                            }

                                            isSelect.remove(parentPosition);
                                            isSelect.add(parentPosition, select);
//                                            guiGeAdapter.notifyDataSetChanged();
                                            /*
                                            * 外部分割 * * * * * * * * * * * * * * * * * * * * * * *
                                            *
                                            * */
                                            //获取到被选中的position
                                            for (int i = 0; i < dataList.size(); i++) {
                                                List<Boolean> selected = isSelect.get(i);
                                                for (int a = 0; a < selected.size(); a++) {
                                                    if (selected.get(a)) {
                                                        positions[i] = String.valueOf(a);
                                                    }
                                                }
                                            }
                                            String selectStates = "";
                                            for (int i = 0; i < positions.length; i++) {
                                                selectStates += positions[i] + ",";
                                            }
                                            Log.e("selectStatus = ", selectStates);
                                            //获取规格名称
                                            String[] ggName = new String[positions.length];
                                            for (int i = 0; i < ggName.length; i++) {
                                                List<HashMap<String, Object>> list =
                                                        (List<HashMap<String, Object>>) dataList
                                                                .get(i).get("guige");
                                                for (int b = 0; b < list.size(); b++) {
                                                    HashMap<String, Object> item = list.get(b);
                                                    if (Integer.parseInt(positions[i]) == b) {
                                                        ggName[i] = (String) item.get("item");
                                                    }
                                                }
                                            }
                                            for (int i = 0; i < ggName.length; i++) {
                                                Log.e("Name" + i + "=", " " + ggName[i]);
                                            }


                                        }
                                    };
                                    childHandlerList.add(handler);
                                }

//                                String[]  arrs=temple.split(",");
                                guiGeAdapter = new CouCouGuiGeAdapter(getApplicationContext(),
                                        dataList, isSelect, parentHandlerList);
                                guiGe.setAdapter(guiGeAdapter);


                            }
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
                    Toast.makeText(getApplication(), "网络故障，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
