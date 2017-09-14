package com.qkxmall.mall.views.hui.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.hui.HuiBuyChoiceSthPageActivity;
import com.qkxmall.mall.views.hui.guige.GuiGeAdapter;
import com.qkxmall.mall.views.hui.guige.GuiGeItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuiToBuyNowActivity extends AppCompatActivity {

    int parentPosition = 0;
    int childPosition = 0;
    ImageView navigation = null;
    LOD lod;

    Button add = null;
    Button buy = null;

   static GuiGeAdapter guiGeAdapter;
    /*
    * 规格列表，含有id，价格，等
    * */
    static List<HashMap<String, Object>> ggList = new ArrayList<>();
    /*
    * 显示规格的列表，仅包含文字
    *
    * */
    public static List<HashMap<String, Object>> dataList = new ArrayList<>();

    //bool
    public static List<List<Boolean>> bllist=new ArrayList<>();
    //默认position
    String[] positions;

    int requestNumber = 0;
    int maxRequestNumber = 2;
    private List<HashMap<String, Object>>[] selectList;

    private TextView name = null;
    public static TextView isSelected = null;
    private static ListView guiGe = null;
    private static TextView price = null;
    private static TextView gg_id = null;

    public static String[] arr;
    public static String mJnos;
  static   Bundle bundle;
  static   JSONArray jsonArray;

    String GID;
    Context context;

    Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if ( GuiGeItemAdapter.tv!=null){
                GuiGeItemAdapter.tv.performClick();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hui_pay_to_cart);
        context = HuiToBuyNowActivity.this;
        GID = getIntent().getStringExtra("id");
        ggList.clear();
        dataList.clear();
        bllist.clear();
        init();
        if (getIntent().getBooleanExtra("addtocart",false)){//如果是加入购物车按钮跳转过来的
            add.setVisibility(View.VISIBLE);
            buy.setText("立即购买");

        }
        lod = new LOD(context);

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取商品规格中...");
        progressDialog.show();
        GetDetail getDetail = new GetDetail(progressDialog);
        String url = API.HUI_PRODUCTS_DETAIL + "&gid=" + GID;
        System.out.println("========GID" + GID);
        BackgroundTask backgroundTask = new BackgroundTask(context, url, getDetail);
        backgroundTask.doInBackground();
//        System.out.println("产品规格ggList" + ggList.toString());
        navigation.setOnClickListener(new OnClick());
        add.setOnClickListener(new OnClick());
        buy.setOnClickListener(new OnClick());



    }

    public static void refreshView(String s, int h,int m,List<HashMap<String,
            Object>> lists) {

        //当前商品 开心豆数量
        System.out.println("!!!!!!!!!!!! ggLsit:"+dataList.toString());
         dataList.clear();
           addData();
        System.out.println("点击的Text：" + s + "点击的list行数：" + h);
        //设置集合保存所选项 符合条件的 map
        List<HashMap<String, Object>> mlist = new ArrayList<>();
        List <HashMap<String, Object>> mDataList=dataList;
//        List li;
//        Object[]  arr1=lists.toArray();
//        Object[]  arr2=new Object[lists.size()];
//        System.arraycopy(arr1, 0, arr2, 0, lists.size());
//        System.out.println("mmmmmmmmmmmm" + arr2.toString());
//
//        li=Arrays.asList(arr2);
//        mDataList=li;

        for (int i = 0; i < ggList.size(); i++) {
            HashMap<String, Object> data = ggList.get(i);//data 中的所有数据
            List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) data.get
                    ("spec_array");//spec_array中的所有数据
            for (int x = 0; x < mapList.size(); x++) {
                HashMap<String, Object> dataMap = mapList.get(x);//每个spec_array中的各个项
//                System.out.println("_+_+_+_+_+_+_+_+_++_" + dataMap.toString());
                if (dataMap.get("value").equals(s)) {
                    System.out.println("符合条件的：" + mapList.toString());
                    mlist.addAll(mapList);
                }
            }
        }

        System.out.println("条件集：" + mlist.toString());

        String[] arr = new String[mDataList.size()];//拿到规格类型数组
        List<List<String>> list2=new ArrayList<>();

        for (int i=0;i<mDataList.size();i++){
            List<String> list=new ArrayList<>();
            list2.add(list);
        }
        for (int i = 0; i < mDataList.size(); i++) {
            HashMap<String, Object> map=( HashMap<String, Object>)mDataList.get(i);
            arr[i] = map.get("title").toString();
        }
        for (int i = 0; i < mlist.size(); i++) {
            for (int y = 0; y < arr.length; y++) {
                if (mlist.get(i).get("name").toString().equals(arr[y])) {
                    list2.get(y).add(mlist.get(i).get("value").toString());
                }
            }
        }
        //删除list中重复的数据
        for(int i=0;i<list2.size();i++){
             for (int j=0;j<list2.get(i).size();j++){
                 for (int z=list2.get(i).size()-1;z>j;z--){
                     if (list2.get(i).get(z).toString().equals(list2.get(i).get(j).toString())){
                         list2.get(i).remove(z);
                     }

                 }
             }

        }

//        System.out.println("mmmmmmmmm  0" + list2.get(0).toString());
//        System.out.println("mmmmmmmmm  1" + list2.get(1).toString());
//        System.out.println("mmmmmmmmm  2" + list2.get(2).toString());

//        mDataList=dataList;
        System.out.println("=======变化前：" + dataList.toString());
        for (int x = 0; x < dataList.size(); x++) {
            List<HashMap<String, Object>> listmap = (List<HashMap<String, Object>>) dataList.get
                    (x).get("guige");
//            List<Boolean> mbl=bllist.get(x);
            for (int y = 0; y < listmap.size(); y++) {
                HashMap<String, Object> hashMap = listmap.get(y);

//                if (y==h){
//
//                    mbl.get(y).compareTo(true);
//                }
                    for (int i=0;i<list2.get(x).size();i++){
                        if (x!=h) {
                            if (list2.get(x).contains(hashMap.get("item").toString())) {
                                break;
                            } else {
                                hashMap.put("item", "暂无");
                            }
                        }
                    }


            }
        }
//------------------------------------------xxxx

        for (int i=0;i<dataList.size();i++){
            List<Boolean> mlists=bllist.get(i);
            for (int y=0;y<mlists.size();y++){
                         if (i==h) {
                             if (y == m) {
                                  mlists.set(y, true);
                             } else {
                                 mlists.set(y,false);
                             }
                         }
            }
        }
        System.out.println("==：" + dataList.toString());
        //刷新适配器
        guiGeAdapter.notifyDataSetChanged();
         System.out.println("!!!!!!!!!!!!!!!! list 数据结构：" + dataList.toString());

    }

    public static void addData(){
        {
            try {

                jsonArray = new JSONArray(bundle.getString("result"));

                System.out.println("wwwwwwwwwwwwwwwwwwwww 最初Json数据：" + bundle
                        .getString("result"));
                for (int m = 0; m < jsonArray.length(); m++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(m);

                    JSONObject spec_array = jsonObject.getJSONObject("spec_array");
                    List<HashMap<String, Object>> specList = new ArrayList<>();
                    for (int i = 0; i < spec_array.length(); i++) {
                        for (int index = 0; index < 100; index++) {
                            if (spec_array.has(String.valueOf(index))) {
                                JSONObject object = spec_array.getJSONObject(String
                                        .valueOf(index));
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

                    for (int i = 0; i < specList.size(); i++) {
                        HashMap<String, Object> map = specList.get(i);
                        HashMap<String, Object> dataMap = new HashMap<>();
                        dataMap.put("title", map.get("name"));
                        dataMap.put("id", map.get(i));
                        String[] value = ((String) map.get("value")).split(",");
                        List<HashMap<String, Object>> valueList = new ArrayList<>();
                        for (int n = 0; n < value.length; n++) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("item", value[n]);
                            valueList.add(hashMap);
                        }

//                        List<Boolean> valueList2 = new ArrayList<>();
//                        for (int x = 0; x < value.length; x++) {
//
//                            valueList2.add(true);
//                        }


                        dataMap.put("guige", valueList);
                        dataList.add(dataMap);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
    }

    public static String kaixindou="";

    public static void setText() {
        String mstring2 = "";
        for (int i = 0; i < arr.length; i++) {
            mstring2 += arr[i].substring(0, arr[i].length() - 1);
        }
//        System.out.println("++++++++++++mstring2"+mstring2);
        for (int i = 0; i < ggList.size(); i++) {
            String mstring = "";
            HashMap<String, Object> data = ggList.get(i);//data 中的所有数据
            List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) data.get
                    ("spec_array");//spec_array中的所有数据
            for (int x = 0; x < mapList.size(); x++) {
                HashMap<String, Object> dataMap = mapList.get(x);//每个spec_array中的各个项
//              for (int f=0;f<dataMap.size();f++)
                mstring += dataMap.get("value");
                if (mstring.equals(mstring2)) {
//                    System.out.println("mstring+++++"+mstring.toString());
                    gg_id.setText(data.get("id").toString());
                    System.out.println("！！！！！！！！！！！ 商品id 1");
                    price.setText(data.get("shop_price").toString());
                    System.out.println("商品可用开心豆是：+++++" + data.get("products_kaixindou"));
                    kaixindou=(String)data.get("products_kaixindou");
                }

            }


        }
    }
       private void init() {
        name = (TextView) findViewById(R.id.name);
        isSelected = (TextView) findViewById(R.id.selected);
        guiGe = (ListView) findViewById(R.id.specList);
        price = (TextView) findViewById(R.id.price);
        gg_id = (TextView) findViewById(R.id.gg_id);
        navigation = (ImageView) findViewById(R.id.navigation);
        add = (Button) findViewById(R.id.add);
        buy = (Button) findViewById(R.id.buy);

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
                            * 获取到商品规格详细信息
                            *
                            * */
                            ProgressDialog progressDialog = new ProgressDialog(context);
                            progressDialog.setMessage("");
                            progressDialog.show();
                            GGHandler ggHandler = new GGHandler(progressDialog);
                            String ggUrl = API.SPGG + "&gid=" + GID;
                            BackgroundTask ggTask = new BackgroundTask(context, ggUrl, ggHandler);
                            ggTask.doInBackground();

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
                                name.setText(jsonObject.getString("name"));
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
                                selectList = new ArrayList[dataList.size()];


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
                                * 开始初始化数据
                                *
                                * */
                                progressDialog.setOnDismissListener(new DialogInterface
                                        .OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
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
                                                    (List<HashMap<String, Object>>) dataList.get
                                                            (i).get("guige");
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
                                        //比对规格，获取最终id
                                        for (int i = 0; i < positions.length; i++) {
                                            Log.e("123", "poi");
                                            Log.e("123", "" + ggList.size());
                                            for (int a = 0; a < ggList.size(); a++) {
                                                HashMap<String, Object> data = ggList.get(a);
                                                System.out.println
                                                        ("=========================================ggList.get(a)" + ggList.get(a));
                                                List<HashMap<String, Object>> mapList =
                                                        (List<HashMap<String, Object>>) data.get
                                                                ("spec_array");
                                                String[] tempString = new String[mapList.size()];
                                                for (int b = 0; b < mapList.size(); b++) {
                                                    HashMap<String, Object> dataMap = mapList.get
                                                            (b);
                                                    tempString[b] = (String) dataMap.get("value");
                                                }
                                                //输出规格信息；
                                                String temple = "";
                                                for (int b = 0; b < tempString.length; b++) {
                                                    temple += tempString[b] + ",";
                                                }
                                                Log.e("GuiGeInfoMation", temple);

                                                for (int b = 0; b < tempString.length; b++) {
                                                    if (tempString[b].equals(ggName[b])) {
                                                        String id = (String) data.get("id");
                                                        String temp = "";
                                                        for (int c = 0; c < ggName.length; c++) {
                                                            temp += ggName[c] + ",";
                                                        }
                                                        isSelected.setText(temp);
                                                        System.out.println("===========isSelected" +
                                                                ".setText(temp)1" + temp);
                                                        price.setText((CharSequence) data.get
                                                                ("shop_price"));
                                                        if(b==0){
                                                            gg_id.setText((CharSequence) data.get
                                                                    ("id"));//商品id 应该默认第个商品
                                                        }

                                                        System.out.println("！！！！！！！！！！！ 商品id 2"+data.get
                                                                ("id"));
                                                        System.out.println("商品可用开心豆2" + data.get
                                                                ("kaixindou"));
                                                        System.out.println("商品id设置1" + data.get
                                                                ("id"));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
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
                                            //比对规格，获取最终id
                                            String[] strarr = isSelected.getText().toString()
                                                    .split(",");
                                            String mstring2 = "";
                                            String mstring = "";
                                            for (int i = 0; i < positions.length; i++) {
                                                for (int a = 0; a < ggList.size(); a++) {
                                                    HashMap<String, Object> data = ggList.get(a);
                                                    List<HashMap<String, Object>> mapList =
                                                            (List<HashMap<String, Object>>) data
                                                                    .get("spec_array");
                                                    String[] tempString = new String[mapList.size
                                                            ()];
                                                    for (int b = 0; b < mapList.size(); b++) {
                                                        HashMap<String, Object> dataMap = mapList
                                                                .get(b);
                                                        tempString[b] = (String) dataMap.get
                                                                ("value");
                                                        mstring += mapList.get(b).get("value");
                                                        mstring2 += strarr[b];
                                                        if (b == mapList.size() - 1) {
                                                            if (mstring.equals(mstring2)) {
                                                                //两个数组相等 设置商品id 和价格
//                                                                c_price.setText((CharSequence)
// data.get
////                                                                        ("shop_price"));
//                                                                gg_id.setText(data.get
//                                                                        ("id").toString());


                                                                System.out.println("--------相同："
                                                                        + data.toString());
                                                            }
                                                            System.out.println
                                                                    ("zzzzzzzzzzzzzzzzzz" +
                                                                            mstring);
                                                            mstring = "";
                                                            mstring2 = "";

                                                        }

                                                        System.out.println("商品详细规格：" + b +
                                                                mapList.get(b).get("value"));

                                                    }
                                                    //输出规格信息；
                                                    String temple = "";
//                                                    for (int b = 0 ; b< tempString.length ;b++){
//                                                        temple+=tempString[b]+",";
//                                                    }
                                                    Log.e("GuiGeInfoMation", temple);

                                                    for (int b = 0; b < tempString.length; b++) {
                                                        if (tempString[b].equals(ggName[b])) {
                                                            String id = (String) data.get("id");
                                                            String temp = "";
                                                            for (int c = 0; c < ggName.length;
                                                                 c++) {
                                                                temp += ggName[c] + ",";
//                                                                arr[c]=ggName[c]+",";
                                                            }
//                                                            isSelected.setText(temp);
                                                            System.out.println
                                                                    ("===========isSelected" +
                                                                            ".setText(temp)2" +
                                                                            temp);
//                                                            c_price.setText((CharSequence) data.get
//                                                                    ("shop_price"));
//
//
//                                                            gg_id.setText((CharSequence) data.get
//                                                                    ("id"));
                                                            System.out.println("商品id改变2" +
                                                                    data.get("id"));
                                                            System.out.println("当前商品尺码" + data
                                                                    .get("spec_array"));

                                                        }
                                                    }


                                                }
                                            }

                                        }
                                    };
                                    childHandlerList.add(handler);
                                }

                                initBl();

                                arr = new String[dataList.size()];
//                                String[]  arrs=temple.split(",");
                                guiGeAdapter = new GuiGeAdapter(context, childHandlerList,
                                        dataList, isSelect, parentHandlerList);
                                guiGe.setAdapter(guiGeAdapter);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "网络故障，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class GGHandler extends Handler {
        ProgressDialog progressDialog;

        public GGHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle ggBundle = msg.getData();
                    if (ggBundle != null) {
                        try {
                            JSONObject ggObject = new JSONObject(ggBundle.getString("result"));
                            JSONArray ggArray = ggObject.getJSONArray("data");
                            for (int i = 0; i < ggArray.length(); i++) {
                                JSONObject object = ggArray.getJSONObject(i);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("id", object.get("id"));
                                data.put("goods_id", object.get("goods_id"));
                                data.put("products_sn", object.get("products_sn"));
                                data.put("products_bus", object.get("products_bus"));
                                data.put("products_kaixindou", object.get("products_kaixindou"));
                                System.out.println("！！！！！！开心豆" + object.get("products_kaixindou"));
                                if(i==0){
                                    kaixindou=object.get("products_kaixindou").toString();
                                }
                                data.put("products_barcode", object.get("products_barcode"));
                                /*
                                * 当商品没有规格参数的时候，此处会抛出异常；
                                * 数据格式:
                                *       JSONObject
                                *       Boolean
                                * */
                                JSONArray speArray = object.getJSONArray("spec_array");
                                List<HashMap<String, Object>> specList = new ArrayList<>();
                                for (int m = 0; m < speArray.length(); m++) {
                                    JSONObject speObject = speArray.getJSONObject(m);
                                    HashMap<String, Object> specMap = new HashMap<>();
                                    specMap.put("id", speObject.get("id"));
                                    specMap.put("name", speObject.get("name"));
                                    specMap.put("value", speObject.get("value"));
                                    specList.add(specMap);
                                }
                                data.put("spec_array", specList);
                                data.put("shop_price", object.get("shop_price"));
                                data.put("market_price", object.get("market_price"));
                                data.put("cost_price", object.get("cost_price"));
                                data.put("min_shop_price", object.get("min_shop_price"));
                                data.put("max_shop_price", object.get("max_shop_price"));
                                data.put("goods_number", object.get("goods_number"));
                                ggList.add(data);
                            }
                            System.out.println("!!!!!!!!!!! gg_list" +ggList.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析异常", Toast.LENGTH_SHORT).show();
                            /*
                            * 抛出异常执行解决方法
                            * 抛出类型错误异常后，执行另一种解析方式：Boolean
                            * */
                            try {
                                JSONObject jsonObject = new JSONObject(ggBundle.getString
                                        ("result"));
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                                Toast.makeText(context, "第二次数据解析异常", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    progressDialog.dismiss();
//                    GuiGeItemAdapter.tv.performClick();
                    mHandler.sendEmptyMessageDelayed(123,1000);
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "网络请求失败，请联系客服", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public void addNum(String num){
        {
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case BackgroundTask.SUCCESS:{
                            Bundle bundle = msg.getData();
                            if (bundle != null){
                                Log.e("result",bundle.getString("result"));
                                try {
                                    JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                    if (jsonObject.getBoolean("flag")){
                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                        HuiToBuyNowActivity.this.finish();
                                    }else {
                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case BackgroundTask.FAILURE:{
                            Toast.makeText(context, "添加失败，请稍后再试", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            };
            String url = API.HUI_ADD_TO_CART+
                    "&uid="+lod.get("USER_INFO","UID","")+
                    "&goods_id="+GID+
                    "&product_id="+gg_id.getText().toString().trim()+"&num="+num;
            BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
            backgroundTask.doInBackground();
        }
    }

    EditText ed2;
    public void addPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入要购买的数量");
        View view = View.inflate(this, R.layout.my_login, null);
        ed2 = (EditText) view.findViewById(R.id.password);
        ed2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        ed2.setKeyListener(new DigitsKeyListener(false, true));
        ed2.setHint("请输入购买数量");
        ed2.setText("1");

        builder.setView(view);

        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addNum(ed2.getText().toString());

            }
        });
        builder.setPositiveButton("取消", null);
        builder.create();
        builder.show();
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navigation: {
                    HuiToBuyNowActivity.this.finish();
//                    GuiGeItemAdapter.tv.performClick();
                }
                break;
                case R.id.add:
                    if(isLegal(gg_id.getText().toString().trim())){
                        addPrompt();
                    }else{
                        Toast.makeText(context,"规格错误",Toast.LENGTH_SHORT).show();
                    }

//                {
//                    Handler handler = new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//                            switch (msg.what) {
//                                case BackgroundTask.SUCCESS: {
//                                    Bundle bundle = msg.getData();
//                                    if (bundle != null) {
//                                        Log.e("result", bundle.getString("result"));
//                                        try {
//                                            JSONObject jsonObject = new JSONObject(bundle
//                                                    .getString("result"));
//                                            if (jsonObject.getBoolean("flag")) {
//                                                Toast.makeText(context, jsonObject.getString
//                                                        ("msg"), Toast.LENGTH_SHORT).show();
//                                                HuiToBuyNowActivity.this.finish();
//                                            } else {
//                                                Toast.makeText(context, jsonObject.getString
//                                                        ("msg"), Toast.LENGTH_SHORT).show();
//                                            }
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT)
//                                                    .show();
//                                        }
//                                    }
//                                }
//                                break;
//                                case BackgroundTask.FAILURE: {
//                                    Toast.makeText(context, "添加失败，请稍后再试", Toast.LENGTH_SHORT)
//                                            .show();
//                                }
//                                break;
//                            }
//                        }
//                    };
//                    String url = API.HUI_ADD_TO_CART +
//                            "&uid=" + lod.get("USER_INFO", "UID", "") +
//                            "&goods_id=" + GID +
//                            "&product_id=" + gg_id.getText().toString().trim();
//                    BackgroundTask backgroundTask = new BackgroundTask(context, url, handler);
//                    backgroundTask.doInBackground();
//                }
                break;
                case R.id.buy:
                    if(isLegal(gg_id.getText().toString().trim())){
                        System.out.println("默认商品 可使用开心豆数量：" + kaixindou);
                        Intent intent = new Intent(context, HuiBuyChoiceSthPageActivity.class);
                        intent.putExtra("pid", gg_id.getText().toString().trim());//商品id
                        intent.putExtra("id", GID);//用户id
                        intent.putExtra("kaixindou",kaixindou);//商品可用开心豆
                        intent.putExtra("name",name.getText().toString());
                        intent.putExtra("guige",isSelected.getText().toString());
                        intent.putExtra("c_price",price.getText().toString());//商品价格
                        startActivity(intent);
                    }else{
                        Toast.makeText(context,"规格错误",Toast.LENGTH_SHORT).show();
                    }



//                isLegal(gg_id.getText().toString().trim());   //判断商品是否合法的 方法
                break;
            }
        }
    }
    public void initBl(){
        for (int i=0;i<dataList.size();i++){
            List<Boolean> mlist=new ArrayList<>();
            List<HashMap<String,Object>> map= (List<HashMap<String, Object>>) dataList.get(i).get("guige");
            for (int y=0;y<map.size();y++){

                    if (y==0){
                        mlist.add(true);
                    }else{
                        mlist.add(false);
                    }

            }
            bllist.add(mlist);
        }
        System.out.println("！！！！！！！！！！！！ bl集合"+bllist.toString());


    }

    public boolean isLegal( String pid){

        String s=isSelected.getText().toString().trim();

        String[] arr= s.split(",");
        System.out.println("!!!!!!!!! ggLsit:长度 " + arr.length);
        System.out.println("!!!!!!!!! arr:长度 " + arr.toString());
      for(int i=0;i<ggList.size();i++){
              HashMap<String,Object> map=ggList.get(i);
           if (map.get("id").equals(pid)){//找到当前选中 商品
               //拿到当前商品的 规格
               List<HashMap<String, Object>> specList=(List<HashMap<String, Object>>)ggList.get(i).get("spec_array");
                  for(int f=0;f<specList.size();f++){

                      if(specList.get(f).get("value").equals(arr[f])){
                          System.out.println("!!!!!!!!!!!!!  所有规格   合法：" );
                      }else{
                          System.out.println("!!!!!!!!!!!!!  所有规格   非法：" );
                          return false;

                      }
//              System.out.println("!!!!!!!!!!!!!  所有规格：" + specList.get(f).get("value"));

                       }


           }

      }
             return true;

    }


}
