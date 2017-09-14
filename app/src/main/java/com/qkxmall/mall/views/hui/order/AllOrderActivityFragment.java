package com.qkxmall.mall.views.hui.order;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.views.MyView.HuiAllFragment0;
import com.qkxmall.mall.views.MyView.HuiAllFragment1;
import com.qkxmall.mall.views.MyView.HuiAllFragment2;
import com.qkxmall.mall.views.MyView.HuiAllFragment3;
import com.qkxmall.mall.views.MyView.HuiAllFragment4;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class AllOrderActivityFragment extends Fragment implements  PullToRefreshBase.OnRefreshListener2<ScrollView>{
    Context context;
    AllOrderActivityFragment arrhui=this;
    private TabHost tabHost = null;
    ImageView navigation = null;
    HuiAllFragment0 huiAllFragment0;
    HuiAllFragment1 huiAllFragment1;
    HuiAllFragment2 huiAllFragment2;
    HuiAllFragment3 huiAllFragment3;
    HuiAllFragment4 huiAllFragment4;
    HuiAllFragment huiAllFragment;
    HuiAllFragment huiPayFragment;
    HuiAllFragment huiDeliveryFragment;
    HuiAllFragment huiEvaluationFragment;
    HuiAllFragment huiReceiveFragment;

    private PullToRefreshScrollView pullToRefresh;
    public AllOrderActivityFragment() {
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_hui_order, container, false);
        pullToRefresh=(PullToRefreshScrollView)rootView.findViewById(R.id.pull_to_refresh1);
        pullToRefresh.setOnRefreshListener(this);
        init(rootView);
        tabHost.addTab(tabHost.newTabSpec("all").setIndicator("所有").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("pay").setIndicator("待付款").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("delivery").setIndicator("待发货").setContent(R.id.tab3));
        tabHost.addTab(tabHost.newTabSpec("post").setIndicator("待收货").setContent(R.id.tab4));
        tabHost.addTab(tabHost.newTabSpec("evaluation").setIndicator("待评价").setContent(R.id.tab5));
        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            TextView tv=(TextView)tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(10);//设置字体的大小；
        }

        //惠购所有订单
        huiAllFragment0=new HuiAllFragment0(context,arrhui);
        // huiAllFragment=new HuiAllFragment();
//        huiAllFragment = new HuiAllFragment();
//        huiAllFragment.newInstance(context);
        Bundle allBundle = new Bundle();
        allBundle.putInt("tag", 0);
        huiAllFragment0.setArguments(allBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab1,huiAllFragment0).commit();
        //惠购待支付订单
        huiAllFragment1=new HuiAllFragment1(context);
        Bundle payBundle = new Bundle();
        payBundle.putInt("tag", 1);
        huiAllFragment1.setArguments(payBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab2,huiAllFragment1).commit();


//        huiPayFragment = new HuiAllFragment();
//        huiPayFragment.newInstance(context);
//        Bundle payBundle = new Bundle();
//        payBundle.putInt("tag", 1);
//        huiPayFragment.setArguments(payBundle);
//        getFragmentManager().beginTransaction().replace(R.id.tab2,huiPayFragment).commit();


        //惠购待发货


//        huiDeliveryFragment = new HuiAllFragment();
//        huiDeliveryFragment.newInstance(context);
        huiAllFragment2=new HuiAllFragment2(context);
        Bundle deliveryBundle = new Bundle();
        deliveryBundle.putInt("tag", 2);
        huiAllFragment2.setArguments(deliveryBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab3,huiAllFragment2).commit();
        //惠购待收货
        huiAllFragment3=new HuiAllFragment3(context,this);
//        huiReceiveFragment = new HuiAllFragment();
//        huiReceiveFragment.newInstance(context);
        Bundle receiveBundle = new Bundle();
        receiveBundle.putInt("tag", 3);
        huiAllFragment3.setArguments(receiveBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab4,huiAllFragment3).commit();
        //惠购待评价
        huiAllFragment4=new HuiAllFragment4(context,this);
//        huiEvaluationFragment = new HuiAllFragment();
//        huiEvaluationFragment.newInstance(context);
        Bundle evaluationBundle = new Bundle();
        evaluationBundle.putInt("tag", 4);
        huiAllFragment4.setArguments(evaluationBundle);
        getFragmentManager().beginTransaction().replace(R.id.tab5,huiAllFragment4).commit();
        if (getArguments() != null){
            tabHost.setCurrentTab(getArguments().getInt("pageID",0));
        }
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("all")){
                    huiAllFragment0.loadmsg();
                }else if(s.equals("pay")){
                    huiAllFragment1.loadmsg();
                }else if(s.equals("delivery")){
                    huiAllFragment2.loadmsg();
                }else if(s.equals("post")){
                    huiAllFragment3.loadmsg();
                }else if(s.equals("evaluation")){
                    huiAllFragment4.loadmsg();
                }
            }
        });
        navigation.setOnClickListener(new OnClick());
        return rootView;
    }


         public void setTab(int i){
             tabHost.setCurrentTab(i);
             if(i == 1){
                 huiAllFragment1.loadmsg();
             }else if(i==2){
                 huiAllFragment2.loadmsg();
             }
         }


    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//        switch (tabHost.getCurrentTab()){
//            case  0:
//                huiAllFragment0.onPullDownToRefresh();
//                break;
//            case  1:
//                huiAllFragment1.onPullDownToRefresh();
//                break;
//            case 2:
//                huiAllFragment2.onPullDownToRefresh();
//                break;
//            case 3:
//                huiAllFragment3.onPullDownToRefresh();
//                break;
//            case 4:
//                huiAllFragment4.onPullDownToRefresh();
//                break;
//        }
        ((AllHuiOrderActivity)getActivity()).finishrefresh();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!下拉刷新,currenttab:" + tabHost.getCurrentTab());
    }


    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++上拉加载");
        switch (tabHost.getCurrentTab()){
            case  0:
                huiAllFragment0.onPullUpToRefresh();
                break;
            case  1:
                huiAllFragment1.onPullUpToRefresh();
                break;
            case 2:
                huiAllFragment2.onPullUpToRefresh();
                break;
            case 3:
                huiAllFragment3.onPullUpToRefresh();
                break;
            case 4:
                huiAllFragment4.onPullUpToRefresh();
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






    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        tabHost = (TabHost) rootView.findViewById(R.id.tabHost);
        tabHost.setup();
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    AllHuiOrderActivity.allHuiOrderActivity.finish();
                }
                break;
            }
        }
    }
}
