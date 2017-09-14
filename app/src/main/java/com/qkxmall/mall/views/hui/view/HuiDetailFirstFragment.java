package com.qkxmall.mall.views.hui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.FragmentPagerAdapter;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.MyView.ArrComments;
import com.qkxmall.mall.views.func.coucou.activity.CouCouListActivity;
import com.qkxmall.mall.views.hui.HuiCommunityPageActivity;
import com.qkxmall.mall.views.hui.ImageFragment;
import com.qkxmall.mall.views.hui.container.AllComments;
import com.qkxmall.mall.views.hui.container.HuiActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HuiDetailFirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HuiDetailFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HuiDetailFirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_GID = "gid";
    private static final String ARG_NAME = "name";
    private static final String ARG_PRICE = "shop_price";
    private static final String ARG_SALE = "sales_number";
    private static final String ARG_BEAN = "kaixindou";
    private static final String ARG_SEND = "fahuodi";
    private static final String ARG_IMAGES = "list_img";


    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    private TextView marketPrice = null;
    private TextView del = null;
    private TextView dell = null;

    private String GID;
    private String NAME;
    private String PRICE = "";
    private String SALE = "";
    private String BEAN = "";
    private String SEND = "";
    private String IMAGES = ",";
    private String market = "";

    private Context context;

    private ImageView navigation = null;
    private ImageView cart = null;
    private ViewPager images = null;
    private TextView name = null;
    private TextView price = null;
    private TextView sale = null;
    private TextView bean = null;
    private TextView send = null;
    private ImageView share = null;


    private TextView reviewNumber = null;
    private TextView tvCouHistory;
    private RatingBar rating = null;
    private TextView reviewInfo = null;
    private TextView noThing = null;
    private TextView huiall;

    private OnFragmentInteractionListener listener;

    public HuiDetailFirstFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            GID = getArguments().getString(ARG_GID);
            System.out.println("!!!!!!!!!! GID:"+GID);
            NAME = getArguments().getString(ARG_NAME);
            PRICE = getArguments().getString(ARG_PRICE);
            SALE = getArguments().getString(ARG_SALE);
            BEAN = getArguments().getString(ARG_BEAN);
            SEND = getArguments().getString(ARG_SEND);
            IMAGES = getArguments().getString(ARG_IMAGES);
            market = getArguments().getString("marketprice");
//            String  IMAGES = getArguments().getString("list_img");
//            Toast.makeText(getActivity(), IMAGES, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.new_fragment_hui_detail_first, container, false);
        init(rootView);
        name.setText(NAME);
        price.setText(PRICE);
        sale.setText(SALE);
        bean.setText(BEAN);
        send.setText(SEND);
        marketPrice.setText(market);
        Log.e("IMAGES",IMAGES);
        String[] imageArray = IMAGES.split(",");
        List<HashMap<String ,Object>> imageList = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0 ; i< imageArray.length;i++){
            fragments.add(ImageFragment.newInstance(context,imageArray[i]));
        }
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(),fragments);
        images.setAdapter(fragmentPagerAdapter);

        loadReview();

        navigation.setOnClickListener(new OnClick());
        share.setOnClickListener(new OnClick());


        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        cart = (ImageView) rootView.findViewById(R.id.cart);
        images = (ViewPager) rootView.findViewById(R.id.images );
        name = (TextView) rootView.findViewById(R.id.name);
        price = (TextView) rootView.findViewById(R.id.price);
        sale = (TextView) rootView.findViewById(R.id.sale);
        bean = (TextView) rootView.findViewById(R.id.bean);
        send = (TextView) rootView.findViewById(R.id.send);
        share = (ImageView) rootView.findViewById(R.id.share);
        tvCouHistory = (TextView)rootView.findViewById(R.id.cou_history);
        tvCouHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CouCouListActivity.class));
            }
        });

        marketPrice = (TextView) rootView.findViewById(R.id.marketPrice);
        del = (TextView) rootView.findViewById(R.id.del);
        dell = (TextView) rootView.findViewById(R.id.dell);
        marketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        del.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        dell.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        reviewNumber = (TextView) rootView.findViewById(R.id.reviewNumber);
        reviewInfo = (TextView) rootView.findViewById(R.id.reviewInfo);
        rating = (RatingBar) rootView.findViewById(R.id.rating);
        noThing = (TextView) rootView.findViewById(R.id.noThing);

        huiall= (TextView) rootView.findViewById(R.id.hui_all);
        huiall.setOnClickListener(new OnClick());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.hui_all:
                    Intent intent2 = new Intent(getActivity(), AllComments.class);//需要用户id和商品id
                    intent2.putExtra("gid", GID);

                    startActivity(intent2);


                    break;
                case R.id.share:
//                    showShare();
                    showShare2();
                    break;
                case R.id.navigation:{
                    HuiActivity.huiActivity.finish();
                }
                    break;
                case R.id.sheQu:{
                    Intent intent = new Intent(context,HuiCommunityPageActivity.class);
                    startActivity(intent);
                }
                    break;
                case R.id.seeAll:{

                }
                    break;
                case R.id.newer:{

                }
                    break;
                case R.id.community:{

                }
                    break;
                case R.id.customService:{

                }
                    break;
            }
        }
    }

    void loadReview(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case BackgroundTask.SUCCESS:{
                        Bundle bundle = msg.getData();
                        if (bundle != null){
                            try {
                                JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                if (jsonObject.getBoolean("flag")){
                                    JSONObject object = jsonObject.getJSONObject("data");
                                    reviewNumber.setText(object.getString("total"));
                                    if (object.getString("total").equals("0")){
                                        noThing.setVisibility(View.GONE);
                                    }else {
                                        rating.setMax(5);
                                        rating.setRating(Float.parseFloat(object.getString("avgmiaoshu")));
                                        Log.e("Rating",object.getString("avgmiaoshu"));

                                        JSONArray rows = object.getJSONArray("rows");
                                        for (int m = 0 ; m < rows.length() ;m++){
                                            JSONObject item = rows.getJSONObject(m);
                                            if (m == 0){
                                                reviewInfo.setText(item.getString("content"));
                                            }
                                        }
                                    }
                                }else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                        break;
                    case BackgroundTask.FAILURE:{
                        Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                        break;
                }
            }
        };
        String address = API.HUI_COMMENT_LIST+"&gid="+GID;
        Log.e("AVGAddress",address);
        BackgroundTask backgroundTask = new BackgroundTask(context,address,handler);
        backgroundTask.doInBackground();
    }

    public void showShare2() {


        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE+"&uid="+lod.get("USER_INFO","UID","'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context,shareAdd,handler);
        backgroundTask.doInBackground();

//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(getActivity(),appID,appSecret);
        wxHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(),appID,appSecret);
        wxCircleHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();
        qZoneSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
//     mController.setShareMedia(new UMImage(getActivity(), "http://www.qkxmall.com/xiazai/"));
        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource
                (getResources(), R.drawable.fenxiang)));

        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(getActivity(), false);
    }
    public void showShare() {

        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE+"&uid="+lod.get("USER_INFO","UID","'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context,shareAdd,handler);
        backgroundTask.doInBackground();

//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(context, appID, appSecret);
        wxHandler.addToSocialSDK();

        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(context, appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
        //mController.setShareMedia(new UMImage(context, "http://www.qkxmall.com/xiazai/"));
        //下面一行的代码是自己添加的
//     mController.setShareImage(new UMImage(context,"http://www.qkxmall.com/xiazai/"));//这条代码和上面的mController.setShareMedia(new UMImage(context, "http://www.qkxmall.com/xiazai/"));等效
        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource(getResources
                (), R.drawable.fenxiang)));

//        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(getActivity(), true);

    }



}
