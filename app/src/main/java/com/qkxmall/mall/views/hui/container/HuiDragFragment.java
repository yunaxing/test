package com.qkxmall.mall.views.hui.container;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.widget.DragLayout;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.func.coucou.activity.CouCouListActivity;
import com.qkxmall.mall.views.func.coucou.activity.LogoEnter;
import com.qkxmall.mall.views.func.coucou.activity.faqiCouCou2;
import com.qkxmall.mall.views.func.coucou.adapter.CouCouHistoryAdapter;
import com.qkxmall.mall.views.func.webview.NewGuidActivity;
import com.qkxmall.mall.views.hui.view.HuiDetailFirstFragment;
import com.qkxmall.mall.views.hui.view.HuiDetailSecondFragment;

import com.qkxmall.mall.views.hui.view.HuiToBuyNowActivity;
import com.qkxmall.mall.views.user.LoginActivity;
import com.qkxmall.mall.views.user.WebFragment;
import com.qkxmall.mall.views.user.WebShowActivity2;

import com.qkxmall.mall.util.DateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class HuiDragFragment extends Fragment {
    String id;
    String name;
    String brand_id;
    String cat_ids;
    String keyword;
    String brief;
    String give_integral;
    String descript;
    String thumb;
    String warn_number;
    String weight;
    String hints;
    String favorite;
    String spec_array;
    String list_img;
    String status_ext;
    String status;
    String model;
    String sales_number;
    String sort;
    String min_shop_price;
    String max_shop_price;
    String shop_price;
    String prom_id;
    String small_pics;
    String kaixindou;
    String param;
    String fahuodi;
    String marketprice;

    private static final String ARG_GID = "gid";
    private String GID;
    private Context context;
    private DragLayout dragLayout = null;
    private HuiDetailFirstFragment huiDetailFirstFragment;
    private HuiDetailSecondFragment huiDetailSecondFragment;
    private TextView loadView;


    private ImageView newer = null;
    private ImageView community = null;
    private ImageView customService = null;
    private ImageView addToCart = null;
    private ImageView buy = null;


    public HuiDragFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            GID = getArguments().getString(ARG_GID);
        }
//        String  IMAGES = getArguments().getString("list_img");
//        Toast.makeText(getActivity(), IMAGES, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_fragment_hui, container, false);
        init(rootView);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取商品信息中...");
        progressDialog.show();
        String address = API.HUI_PRODUCTS_DETAIL + "&gid=" + GID;
        System.out.println("!!!!!!!!!!!!!!!!! 慧购 url " + address);
        Log.e("Address", address);
        LoadData loadData = new LoadData(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
        backgroundTask.doInBackground();



        newer.setOnClickListener(new OnClick());
        community.setOnClickListener(new OnClick());
        customService.setOnClickListener(new OnClick());
//        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context
// .MODE_PRIVATE);
//        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
//        if (isLogin) {
//            buy.setOnClickListener(new OnClick());
//            addToCart.setOnClickListener(new OnClick());
//        }else {
//            buy.setOnClickListener(new Click());
//            addToCart.setOnClickListener(new Click());
//        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context
                .MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            buy.setOnClickListener(new OnClick());
            addToCart.setOnClickListener(new OnClick());
        } else {
            buy.setOnClickListener(new Click());
            addToCart.setOnClickListener(new Click());
        }


    }

    private void load() {
        Bundle bundle = new Bundle();
        bundle.putString("gid", GID);
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("brand_id", brand_id);
        bundle.putString("cat_ids", cat_ids);
        bundle.putString("keyword", keyword);
        bundle.putString("brief", brief);
        bundle.putString("give_integral", give_integral);
        bundle.putString("descript", descript);
        bundle.putString("thumb", thumb);
        bundle.putString("warn_number", warn_number);
        bundle.putString("weight", weight);
        bundle.putString("hints", hints);
        bundle.putString("favorite", favorite);
        bundle.putString("spec_array", spec_array);
        bundle.putString("list_img", list_img);
        bundle.putString("status_ext", status_ext);
        bundle.putString("status", status);
        bundle.putString("model", model);
        bundle.putString("sales_number", sales_number);
        bundle.putString("sort", sort);
        bundle.putString("min_shop_price", min_shop_price);
        bundle.putString("max_shop_price", max_shop_price);
        bundle.putString("shop_price", shop_price);
        bundle.putString("prom_id", prom_id);
        bundle.putString("small_pics", small_pics);
        bundle.putString("kaixindou", kaixindou);
        bundle.putString("param", param);
        bundle.putString("fahuodi", fahuodi);
        bundle.putString("marketprice", marketprice);

        huiDetailFirstFragment = new HuiDetailFirstFragment();
        huiDetailFirstFragment.newInstance(context);
        huiDetailFirstFragment.setArguments(bundle);
        huiDetailSecondFragment = new HuiDetailSecondFragment();
        huiDetailSecondFragment.newInstance(context);
        huiDetailSecondFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.first, huiDetailFirstFragment).add(R.id
                .second, huiDetailSecondFragment).commit();
        dragLayout.setNextPageListener(new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                huiDetailSecondFragment.newInstance(context);
            }
        });

    }

    private void init(View rootView) {
        dragLayout = (DragLayout) rootView.findViewById(R.id.drag);
        newer = (ImageView) rootView.findViewById(R.id.newer);
        community = (ImageView) rootView.findViewById(R.id.community);
        customService = (ImageView) rootView.findViewById(R.id.customService);
        addToCart = (ImageView) rootView.findViewById(R.id.addToCart);
        buy = (ImageView) rootView.findViewById(R.id.buy);
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    private class FQCOUCOU extends Handler {
        ProgressDialog progressDialog;

        public FQCOUCOU(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONObject data = object.getJSONObject("data");
                            if(object.getBoolean("flag")){
                                Intent intent = new Intent(context,faqiCouCou2.class);
                                intent.putExtra("id", GID);
                                intent.putExtra("imgs",list_img);
                                intent.putExtra("price",shop_price);
                                intent.putExtra("cid",data.getString("id").toString());
                                if(addToCart != null){
                                   if(addToCart.getTag().toString().equals("1")){
                                       intent.putExtra("create_pay","1");
                                    }else {
                                       intent.putExtra("create_pay","0");
                                   }
                                }

                                context.startActivity(intent);
                            }else {
                                Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadData extends Handler {
        ProgressDialog progressDialog;

        public LoadData(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.e("Result", bundle.getString("result"));
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                id = jsonObject.getString("id");
                                name = jsonObject.getString("name");
                                brand_id = jsonObject.getString("brand_id");
                                cat_ids = jsonObject.getString("cat_ids");
                                keyword = jsonObject.getString("keyword");
                                brief = jsonObject.getString("brief");
                                give_integral = jsonObject.getString("give_integral");
                                descript = jsonObject.getString("descript");
                                thumb = jsonObject.getString("thumb");
                                warn_number = jsonObject.getString("warn_number");
                                weight = jsonObject.getString("weight");
                                hints = jsonObject.getString("hits");
                                favorite = jsonObject.getString("favorite");
                                spec_array = jsonObject.getString("spec_array");
                                list_img = jsonObject.getString("list_img");
                                status_ext = jsonObject.getString("status_ext");
                                status = jsonObject.getString("status");
                                model = jsonObject.getString("model");
                                sales_number = jsonObject.getString("sales_number");
                                sort = jsonObject.getString("sort");
                                min_shop_price = jsonObject.getString("min_shop_price");
                                max_shop_price = jsonObject.getString("max_shop_price");
                                shop_price = jsonObject.getString("shop_price");
                                prom_id = jsonObject.getString("prom_id");
                                small_pics = jsonObject.getString("small_pics");
                                kaixindou = jsonObject.getString("kaixindou");
                                param = jsonObject.getString("param");
                                fahuodi = jsonObject.getString("fahuodi");
                                marketprice = jsonObject.getString("marketprice");
                                JSONObject coucou = jsonObject.getJSONObject("coucou");
                                int status = coucou.getInt("btn_stat");
                                if(status == 0){
                                    Toast.makeText(context, "发起凑凑", Toast.LENGTH_SHORT).show();
                                    addToCart.setImageResource(R.mipmap.fqcc);
                                    addToCart.setTag(1);
                                }else {
                                    addToCart.setTag(0);
                                    addToCart.setImageResource(R.mipmap.cycc);
                                }
                            }
                            load();
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
                    Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addToCart: {
                    if(addToCart.getTag().toString().equals("1")){
                        Intent intent = new Intent(context, LogoEnter.class);
                    intent.putExtra("id", GID);
                    intent.putExtra("addtocart", true);
                    context.startActivity(intent);
                    SharedPreferences preferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
                    preferences.getString("UID","");
                    String address = API.FQ_COUCOU+"&goods_id="+GID+"&user_id="+ preferences.getString("UID","")+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+GID+preferences.getString("UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
                    System.out.println("!!!!!!!!!!!!!!!!! 慧购 url " + address);
                    Log.e("Address", address);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("发起凑凑...");
                    progressDialog.show();
                    FQCOUCOU loadData = new FQCOUCOU(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
                    backgroundTask.doInBackground();
                    }else {
                        getCouCouList();
                    }
//



                }
                break;
                case R.id.buy: {
                    Intent intent = new Intent(context, HuiToBuyNowActivity.class);
                    intent.putExtra("id", GID);
                    context.startActivity(intent);
                }
                break;
                case R.id.customService: {
                    if(isQQClientAvailable(getActivity())){
                        String url = "mqqwpa://im/chat?chat_type=wpa&uin=1113264197";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }else {
                        Toast.makeText(getActivity(),"请先下载安装QQ",Toast.LENGTH_LONG);
                    }
                }
                break;
                case R.id.newer: {
                    Intent intent = new Intent(context, NewGuidActivity.class);
//                    intent.putExtra("type", WebFragment.HUI_NEW_HELP);
                    context.startActivity(intent);
                }
                break;
                case R.id.community: {
                    Intent intent = new Intent(context, LunTanActivity.class);
                    context.startActivity(intent);
                }
                break;
            }
        }
    }
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void getCouCouList(){
        String address = API.COUCOU_LIST+
                "&goods_id="+GID+
                "&ispay="+1+
                "&status="+0+
                "&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+GID+1+0+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());

        Log.e("Address", address);
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("凑凑历史...");
        progressDialog.show();
        COUCOULIST loadData = new COUCOULIST(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(getActivity(), address, loadData);
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
//                            ArrayList<HistoryItem> itemList = new ArrayList<>();
//                            for(int i=0;i<jsonArray.length(); i++){
//                                HistoryItem item = new HistoryItem();
//                                JSONObject object = jsonArray.getJSONObject(i);
//                                item.setCreater(object.getString("create_user"));
//                                item.setStatus(object.getInt("status"));
//                                itemList.add(item);
//                            }
                            JSONObject object = jsonArray.getJSONObject(0);
                            Intent intent = new Intent(context,faqiCouCou2.class);
                            intent.putExtra("id", GID);
                            intent.putExtra("imgs",list_img);
                            intent.putExtra("price",shop_price);
                            intent.putExtra("cid",object.getString("id").toString());
                            if(addToCart != null){
                                if(addToCart.getTag().toString().equals("1")){
                                    intent.putExtra("create_pay","1");
                                }else {
                                    intent.putExtra("create_pay","0");
                                }
                            }

                            context.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}

