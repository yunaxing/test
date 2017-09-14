package com.qkxmall.mall.views.cloud.detail;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.define.override.WebView;
import com.qkxmall.mall.nets.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link CloudSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloudSecondFragment extends Fragment {
    Context context;
    HashMap<String ,Object> data = new HashMap<>();

    private View progressBar;
    private boolean hasInited = false;
    TextView pic,text;

    private ViewPager detail = null;

    private String html;
    private String html1;
    private String html2;


    public CloudSecondFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }
    public void setData(HashMap<String ,Object> data) {
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cloud_second, container, false);

        progressBar = rootView.findViewById(R.id.progressbar);
        pic = (TextView) rootView.findViewById(R.id.pic);
        text = (TextView) rootView.findViewById(R.id.text);
        detail = (ViewPager) rootView.findViewById(R.id.detail);

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();

        LoadHtmlHandler loadHtmlHandler = new LoadHtmlHandler(progressDialog);
        String uel = API.HUI_PRODUCTS_DETAIL+"&gid="+data.get("goods_id");
        BackgroundTask backgroundTask = new BackgroundTask(context,uel,loadHtmlHandler);
        backgroundTask.doInBackground();


        return rootView;
    }


    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pic:
                    pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    pic.setTextColor(getResources().getColor(R.color.white));
                    text.setTextColor(getResources().getColor(R.color.text_black));
                    text.setBackgroundColor(getResources().getColor(R.color.white));
                    detail.setCurrentItem(0);
                    System.out.print("+++++++++++++++++++++++++++s云购html1：" + html1);
                    break;
                case R.id.text:
                    text.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    text.setTextColor(getResources().getColor(R.color.white));
                    pic.setTextColor(getResources().getColor(R.color.text_black));
                    pic.setBackgroundColor(getResources().getColor(R.color.white));
                    detail.setCurrentItem(1);
                    System.out.print("+++++++++++++++++++++++++++s云购html2：" + html2);
                    break;
            }
        }

    }

    void  loadHtml(){
        String[] temp = html.split("_ueditor_page_break_tag_");
        //Judge length of temp descript
        if (temp.length == 2) {
            html1 = temp[0];
            html2 = temp[1];
        }else {
            html1 = html2 = temp[0];
        }
//        List<Fragment> list = new ArrayList<>();
//        Detail1Fragment detail1Fragment = new Detail1Fragment();
//        detail1Fragment.newInstance(context, html1);
//
//        Detail2Fragment detail2Fragment = new Detail2Fragment();
//        detail2Fragment.newInstance(context,html2);
//        System.out.println("+++++++++++++++++++++云购html1:" + html1);
//        System.out.println("+++++++++++++++++++++云购html2:" + html2);
//        list.add(detail2Fragment);
//        list.add(detail1Fragment);

        progressBar.setVisibility(View.GONE);

        List<View> viewList = new ArrayList<>();
        {
            View h1 = LayoutInflater.from(context).inflate(R.layout.fragment_detail2,null);
            WebView web1 = (WebView) h1.findViewById(R.id.detail);
            if (null != web1 && !hasInited) {
                hasInited = true;
                web1.loadDataWithBaseURL(null, html2, "text/html", "utf-8", null);
                web1.getSettings().setJavaScriptEnabled(true);
                web1.getSettings().setDisplayZoomControls(true);
                web1.getSettings().setSupportZoom(true);
                web1.getSettings().setUseWideViewPort(true);
                web1.getSettings().setLoadWithOverviewMode(true);
                web1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                web1.getSettings().setLoadWithOverviewMode(true);
            }

            viewList.add(h1);
            View h2 = LayoutInflater.from(context).inflate(R.layout.fragment_detail2,null);
            WebView web2 = (WebView) h2.findViewById(R.id.detail);

            hasInited = false;
            if (null != web2 && !hasInited) {
                hasInited = true;
                web2.loadDataWithBaseURL(null, html1, "text/html", "utf-8", null);
                System.out.println("WebView" + html1);
                web2.getSettings().setJavaScriptEnabled(true);
                web2.getSettings().setDisplayZoomControls(true);
                web2.getSettings().setSupportZoom(true);
                web2.getSettings().setLoadWithOverviewMode(true);
            }
            viewList.add(h2);
        }
        PagerAdapter pagerAdapter = new PagerAdapter(viewList);
        detail.setAdapter(pagerAdapter);
//        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(),list);
//        detail.setAdapter(fragmentPagerAdapter);

        pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
        pic.setTextColor(getResources().getColor(R.color.white));
        text.setTextColor(getResources().getColor(R.color.text_black));
        text.setBackgroundColor(getResources().getColor(R.color.white));

        pic.setOnClickListener(new OnClick());
        text.setOnClickListener(new OnClick());
        detail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (detail.getCurrentItem() == 0){
                    pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    pic.setTextColor(getResources().getColor(R.color.white));
                    text.setTextColor(getResources().getColor(R.color.text_black));
                    text.setBackgroundColor(getResources().getColor(R.color.white));

                }else {
                    text.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    text.setTextColor(getResources().getColor(R.color.white));
                    pic.setTextColor(getResources().getColor(R.color.text_black));
                    pic.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class LoadHtmlHandler extends Handler{
        ProgressDialog progressDialog;

        public LoadHtmlHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            System.out.println("zzzzzzzzzzzzzzzzzzzzzz" + bundle.getString("result"));
                            for (int i = 0 ; i< jsonArray.length() ;i++){

                                JSONObject object = jsonArray.getJSONObject(i);
                                html = object.getString("descript");

                                Log.e("HTML",html);
                                loadHtml();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "网络请求失败，请重试", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            progressDialog.dismiss();
        }
    }

}
