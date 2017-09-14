package com.qkxmall.mall.views.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.Category;
import com.qkxmall.mall.beans.ChildItem;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.views.ScrollViewInViewPager;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.nets.JD;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
//import com.qkxmall.mall.views.fragment.categoryadapter.gridviewadapter;
import com.qkxmall.mall.views.fragment.categoryadapter.listviewadapter;
import com.qkxmall.mall.views.func.adapter.gridviewadapter;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;
import com.umeng.socialize.utils.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xingyuna on 2017/5/6.
 */

public class AllBuyMenuFragment2 extends Fragment implements View.OnClickListener{
    View rootView;
    private ArrayList<Category> categories = new ArrayList<>();
    private ListView listContent;
    private GridView gridContent;
    private gridviewadapter gridadapter;
    private listviewadapter listadapter;
    private TextView  tvTitle;
    Typeface typeface;
    private View view = null;
    List<View> list = new ArrayList<>();
    private ScrollViewInViewPager viewPager = null;
    private View huiView = null;
    private View cloudView = null;
    private TextView cloudClassify = null;
    private TextView huiClassify = null;
    TextView class1 = null;
    TextView class2 = null;
    TextView class3 = null;
    TextView class4 = null;
    TextView class5 = null;
    TextView class6 = null;
    TextView class7 = null;
    TextView class8 = null;
    TextView class9 = null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_all_buy_menu2, null);

        init();
//        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/palamecia_titling.ttf");
//        cloudClassify.setTypeface(typeface);
//        huiClassify.setTypeface(typeface);
//        cloudView = LayoutInflater.from(getActivity()).inflate(R.layout.all_buy_menu_cloud_menu_layout, null);
//        class1 = (TextView) cloudView.findViewById(R.id.shoujizhineng);
//        class2 = (TextView) cloudView.findViewById(R.id.diannaobangong);
//        class3 = (TextView) cloudView.findViewById(R.id.xishuhufu);
//        class4 = (TextView) cloudView.findViewById(R.id.chuandaimingpin);
//        class5 = (TextView) cloudView.findViewById(R.id.jingmeishipin);
//        class6 = (TextView) cloudView.findViewById(R.id.yundonghuwai);
//        class7 = (TextView) cloudView.findViewById(R.id.shenghuoyongpin);
//        class8 = (TextView) cloudView.findViewById(R.id.chuangyilipin);
//        class9 = (TextView) cloudView.findViewById(R.id.qita);
//        class1.setOnClickListener(this);
//        class2.setOnClickListener(this);
//        class3.setOnClickListener(this);
//        class4.setOnClickListener(this);
//        class5.setOnClickListener(this);
//        class6.setOnClickListener(this);
//        class7.setOnClickListener(this);
//        class8.setOnClickListener(this);
//        class9.setOnClickListener(this);
//        list.add(cloudView);

        huiView = LayoutInflater.from(getActivity()).inflate(R.layout.all_buy_menu_hui_menu_layout2, null);
        listContent = (ListView)huiView.findViewById(R.id.listview_content);
        listContent.setVisibility(View.GONE);
        tvTitle = (TextView)rootView.findViewById(R.id.tv_title);
        gridContent = (GridView)huiView.findViewById(R.id.gridview_content);
        list.add(huiView);

        viewPager.setAdapter(new AdsViewPagerAdapter(list));
        viewPager.setCurrentItem(1);
        if (viewPager.getCurrentItem() == 0) {
            cloudClassify.setTextColor(getResources().getColor(R.color.white));
            cloudClassify.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
            huiClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
            huiClassify.setBackgroundColor(getResources().getColor(R.color.white));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0) {
                    cloudClassify.setTextColor(getResources().getColor(R.color.white));
                    cloudClassify.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
                    huiClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    huiClassify.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (viewPager.getCurrentItem() == 1) {
                    cloudClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    cloudClassify.setBackgroundColor(getResources().getColor(R.color.white));
                    huiClassify.setTextColor(getResources().getColor(R.color.white));
                    huiClassify.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArrayList<ChildItem>  items = new ArrayList<>();
                view.setSelected(true);
                if(categories !=null && categories.size()>0 && categories.get(position).getChidCategorys()!=null){

                    for(int j=0;j<categories.get(position).getChidCategorys().size(); j++){
                        for(int t = 0; t<categories.get(position).getChidCategorys().get(j).getChilditems().size()%3; t++){
                            ChildItem chiditem = new ChildItem();
                            categories.get(position).getChidCategorys().get(j).getChilditems().add(chiditem);

                        }
                        for(int m= 0; m<categories.get(position).getChidCategorys().get(j).getChilditems().size(); m++){
                            if (m == 0) {
                                categories.get(position).getChidCategorys().get(j).getChilditems().get(0).setBigname(categories.get(position).getChidCategorys().get(j).getName());
                            }
                            if(m ==2 || m==1){
                                categories.get(position).getChidCategorys().get(j).getChilditems().get(m).setBigname("invisible");
                            }
                            if(m > 2){
                                categories.get(position).getChidCategorys().get(j).getChilditems().get(m).setBigname("gone");
                            }
                        }

                        items.addAll(categories.get(position).getChidCategorys().get(j).getChilditems());
                    }
                }

                gridadapter = new gridviewadapter(getActivity(),categories);

                gridContent.setAdapter(gridadapter);
            }
        });
        gridContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(((ChildItem)gridadapter.getItem(i)).getName()==null || ((ChildItem)gridadapter.getItem(i)).getName().equals("")){
                    return;
                }
                Intent intent = new Intent(getActivity(),HuiClassItemsActivity.class);
                intent.putExtra("categoryTitle",((ChildItem)gridadapter.getItem(i)).getName());
                intent.putExtra("category_id",((ChildItem)gridadapter.getItem(i)).getId());
                startActivity(intent);
            }
        });
        getAllcategory();
        return rootView;
    }
    private void init() {
        viewPager = (ScrollViewInViewPager) rootView.findViewById(R.id.all_buy_menu_hui_view_pager);
        cloudClassify = (TextView) rootView.findViewById(R.id.all_buy_menu_cloud_classify);
        huiClassify = (TextView) rootView.findViewById(R.id.all_buy_menu_hui_classify);
        huiClassify.setOnClickListener(this);
        cloudClassify.setOnClickListener(this);

    }
    private void getAllcategory(){
        String urlsixImage = API.ALL_CATEGORY;
        getALLCategory loadsixImage = new getALLCategory();
        BackgroundTask advertisement1 = new BackgroundTask(getActivity(), urlsixImage, loadsixImage);
        advertisement1.doInBackground();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            listContent.getChildAt(0).setSelected(true);
        }
    };

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(),AllCloudClassItem.class);
        switch (view.getId()){
            case R.id.all_buy_menu_cloud_classify:
//                viewPager.setCurrentItem(0);
                break;
            case R.id.all_buy_menu_hui_classify:
                viewPager.setCurrentItem(1);
                break;
            case R.id.shoujizhineng:
                intent.putExtra("categoryTitle","手机智能");
                intent.putExtra("category_id" , "1");
                startActivity(intent);
                break;
            case R.id.diannaobangong:
                intent.putExtra("categoryTitle", "电脑办公");
                intent.putExtra("category_id" , "2");
                startActivity(intent);
                break;
            case R.id.xishuhufu:
                intent.putExtra("categoryTitle","洗漱护肤");
                intent.putExtra("category_id" , "3");
                startActivity(intent);
                break;
            case R.id.chuandaimingpin:
                intent.putExtra("categoryTitle","穿戴名品");
                intent.putExtra("category_id" , "4");
                startActivity(intent);
                break;
            case R.id.jingmeishipin:
                intent.putExtra("categoryTitle","精美饰品");
                intent.putExtra("category_id" , "5");
                startActivity(intent);
                break;
            case R.id.yundonghuwai:
                intent.putExtra("categoryTitle","运动户外");
                intent.putExtra("category_id" , "6");
                startActivity(intent);
                break;
            case R.id.shenghuoyongpin:
                intent.putExtra("categoryTitle","生活用品");
                intent.putExtra("category_id" , "7");
                startActivity(intent);
                break;
            case R.id.chuangyilipin:
                intent.putExtra("categoryTitle","创意礼品");
                intent.putExtra("category_id" , "8");
                startActivity(intent);
                break;
            case R.id.qita:
                intent.putExtra("categoryTitle","其它");
                intent.putExtra("category_id" , "9");
                startActivity(intent);
                break;
        }

    }

    private class getALLCategory extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(getActivity());
                        HashMap<String, Object> data = new HashMap<>();
                        try {
                            data = jd.decode(bundle.getString("result"), API.ALL_CATEGORY);
                            categories = (ArrayList<Category>) data.get("category");
                            listadapter= new listviewadapter(getActivity(),categories);
                            listContent.setAdapter(listadapter);
                            tvTitle.setText(categories.get(0).getName());
                            Log.v("allcate","mList=="+categories.get(0).getChidCategorys().size());
                            ArrayList<ChildItem>  items = new ArrayList<>();
//                            if(categories !=null && categories.size()>0 && categories.get(0).getChidCategorys()!=null){

//                            for(int m1=0; m1<categories.size(); m1++){
//
//
//                                for(int j=0;j<categories.get(m1).getChidCategorys().size(); j++){
//                                    for(int t = 0; t<categories.get(m1).getChidCategorys().get(j).getChilditems().size()%4; t++){
//                                        ChildItem chiditem = new ChildItem();
//                                        categories.get(m1).getChidCategorys().get(j).getChilditems().add(chiditem);
//
//                                    }
//                                    for(int m= 0; m<categories.get(m1).getChidCategorys().get(j).getChilditems().size(); m++){
//                                        if (m == 0) {
//                                            categories.get(m1).getChidCategorys().get(j).getChilditems().get(m).setBigname(categories.get(m1).getChidCategorys().get(j).getName());
//                                        }
//                                        if(m ==2 || m==1 || m==3){
//                                            categories.get(m1).getChidCategorys().get(j).getChilditems().get(m).setBigname("invisible");
//                                        }
//                                        if(m > 3){
//                                            categories.get(m1).getChidCategorys().get(j).getChilditems().get(m).setBigname("gone");
//                                        }
//                                        if(m > 7){
//                                            categories.get(m1).getChidCategorys().get(j).getChilditems().get(m).setBigname("none");
//
//                                        }
//                                    }
//
//                                    items.addAll(categories.get(m1).getChidCategorys().get(j).getChilditems());
//                                }
//                            }

                            gridadapter = new gridviewadapter(getActivity(),categories);
//                            listContent.getChildAt(0).setSelected(true);
                            handler.sendEmptyMessageDelayed(10,100);
                            gridContent.setAdapter(gridadapter);
                            Log.v("allcate","allcate=="+categories.get(0).getChidCategorys());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(getActivity(), "数据获取失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
