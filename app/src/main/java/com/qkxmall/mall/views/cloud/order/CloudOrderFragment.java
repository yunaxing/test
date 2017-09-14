package com.qkxmall.mall.views.cloud.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import  com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.MyView.CloudAllFragent0;
import com.qkxmall.mall.views.MyView.CloudAllFragent1;
import com.qkxmall.mall.views.MyView.CloudAllFragent2;
import com.qkxmall.mall.views.MyView.CloudAllFragent3;
import com.qkxmall.mall.views.MyView.CloudAllFragent4;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class CloudOrderFragment extends Fragment  implements  PullToRefreshBase.OnRefreshListener2<ScrollView>{
    Context context;
    private TabHost tabHost = null;

    ImageView navigation = null;
    private  PullToRefreshScrollView pullToRefresh;
    CloudAllFragment cloudAllFragment;
    CloudAllFragment cloudWaitFragment;
    CloudAllFragment cloudHappyFragment;
    CloudAllFragment cloudEvaluationFragment;
    CloudAllFragment cloudReceiveFragment;

    CloudAllFragent1 cloudAllFragent1;
    CloudAllFragent2 cloudAllFragent2;
    CloudAllFragent3 cloudAllFragent3;
//    CloudAllFragent4 cloudAllFragent4;
    CloudAllFragent0 cloudAllFragent0;

    public CloudOrderFragment() {
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_cloud_order, container, false);
        pullToRefresh=(PullToRefreshScrollView)rootView.findViewById(R.id.pull_to_refresh);
        pullToRefresh.setOnRefreshListener(this);
        init(rootView);
        tabHost.addTab(tabHost.newTabSpec("all").setIndicator("所有").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("pay").setIndicator("待揭晓").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("delivery").setIndicator("开心果").setContent(R.id.tab3));
        tabHost.addTab(tabHost.newTabSpec("post").setIndicator("待收货").setContent(R.id.tab4));
//        tabHost.addTab(tabHost.newTabSpec("evaluation").setIndicator("待评价").setContent(R.id.tab5));

        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            TextView tv=(TextView)tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(10);//设置字体的大小；
        }
        //云购所有订单
        cloudAllFragent0=new CloudAllFragent0(context);
//        cloudAllFragment = new CloudAllFragment();
//        cloudAllFragment.newInstance(context);
        Bundle allBundle = new Bundle();
        allBundle.putInt("tag", 0);
        cloudAllFragent0.setArguments(allBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab1,cloudAllFragent0).commit();
        //云购待揭晓

        cloudAllFragent1=new CloudAllFragent1(context);
//        cloudWaitFragment = new CloudAllFragment();
//        cloudWaitFragment.newInstance(context);
        Bundle payBundle = new Bundle();
        payBundle.putInt("tag", 1);
        cloudAllFragent1.setArguments(payBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab2,cloudAllFragent1).commit();
        //云购开心果
        cloudAllFragent2=new CloudAllFragent2(context);
//        cloudHappyFragment = new CloudAllFragment();
//        cloudHappyFragment.newInstance(context);
        Bundle deliveryBundle = new Bundle();
        deliveryBundle.putInt("tag", 2);
        cloudAllFragent2.setArguments(deliveryBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab3,cloudAllFragent2).commit();
        //云购待收货
        cloudAllFragent3=new CloudAllFragent3(context);
//        cloudReceiveFragment = new CloudAllFragment();
//        cloudReceiveFragment.newInstance(context);
        Bundle receiveBundle = new Bundle();
        receiveBundle.putInt("tag", 3);
        cloudAllFragent3.setArguments(receiveBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab4,cloudAllFragent3).commit();
        //云购待评价
//        cloudAllFragent4=new CloudAllFragent4(context);
//        cloudEvaluationFragment = new CloudAllFragment();
//        cloudEvaluationFragment.newInstance(context);
        Bundle evaluationBundle = new Bundle();
        evaluationBundle.putInt("tag", 4);
//        cloudAllFragent4.setArguments(evaluationBundle);
//        getFragmentManager().beginTransaction().replace(R.id.tab5,cloudAllFragent4).commit();
        if (getArguments() != null){
            tabHost.setCurrentTab(getArguments().getInt("pageID",0));
        }
        navigation.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        tabHost = (TabHost) rootView.findViewById(R.id.tabHost);
        tabHost.setup();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        switch (tabHost.getCurrentTab()){
            case  0:
                cloudAllFragent0.onPullDownToRefresh();
                break;
            case  1:
                cloudAllFragent1.onPullDownToRefresh();
                break;
            case 2:
                cloudAllFragent2.onPullDownToRefresh();
                break;
            case 3:
                cloudAllFragent3.onPullDownToRefresh();
                break;
            case 4:
//                cloudAllFragent4.onPullDownToRefresh();
                break;
        }
        System.out.println("下拉刷新,currenttab:" + tabHost.getCurrentTab());
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        System.out.println("上拉加载");
        switch (tabHost.getCurrentTab()){
            case  0:
                cloudAllFragent0.onPullUpToRefresh();
                break;
            case  1:
                cloudAllFragent1.onPullUpToRefresh();
                break;
            case 2:
                cloudAllFragent2.onPullUpToRefresh();
                break;
            case 3:
                cloudAllFragent3.onPullUpToRefresh();
                break;
            case 4:
//                cloudAllFragent4.onPullUpToRefresh();
                break;
        }
    }
    /**
     * 刷新结束调用此方法
     * */
    public   void  finishrefresh(){
        if(pullToRefresh!=null){

            pullToRefresh.onRefreshComplete();
        }else {
            System.out.println("pullToRefresh is null");
        }


    }
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    AllCloudOrderActivity.allCloudOrderActivity.finish();
                }
                break;
            }
        }
    }
}
