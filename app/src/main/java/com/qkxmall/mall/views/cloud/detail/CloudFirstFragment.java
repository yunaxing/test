package com.qkxmall.mall.views.cloud.detail;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.MyView.HistoryActivity;
import com.qkxmall.mall.views.hui.container.AllComments;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link CloudFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloudFirstFragment extends Fragment {
    Context context;
    Handler handler;
    HashMap<String, Object> data = new HashMap<>();

    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    private ImageView share = null;
    private ImageView navigation = null;
    private ViewPager viewPager = null;
    private TextView name = null;
    private TextView price = null;
    private ProgressBar progress = null;
    private TextView total = null;
    private TextView current = null;
    private TextView remain = null;
    private TextView push = null;
    private EditText number = null;
    private TextView add = null;

    private TextView evaluateNumber = null;

    private TextView evaluationInfo = null;
    private RatingBar rating = null;


    private String id;
    private String term;

    TextView arrPing;//全部评论
    TextView historys;//历史记录

    private String cid;

    public CloudFirstFragment(String cid) {
        this.cid=cid;
    }

    public void newInstance(Context context, HashMap<String, Object> data, Handler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    int totalNum;
    int curNum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cloud_first, container, false);
        init(rootView);
        name.setText((CharSequence) data.get("name"));
        price.setText((CharSequence) data.get("c_totalprice"));
        totalNum= Integer.parseInt((String)data.get("totalnum"));
        curNum= Integer.parseInt((String)data.get("curnum"));
        total.setText((CharSequence) data.get("totalnum"));
        current.setText((CharSequence) data.get("curnum"));

        id = (String) data.get("id");
        term = (String) data.get("c_term");

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        String cloudEvaluationAddress ="http://www.qkxmall.com/index.php?m=api&c=cloud&a=getcloudinfo&data=cloud"+ "&cid=" + id ;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!! 评论 url：" + cloudEvaluationAddress);

        ProductsEvaluation productsEvaluation = new ProductsEvaluation(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(context, cloudEvaluationAddress,
                productsEvaluation);
        backgroundTask.doInBackground();

        evaluateNumber.setText((CharSequence) data.get("commnum"));

        int remainText = Integer.parseInt((String) data.get("totalnum")) - Integer.parseInt(
                (String) data.get("curnum"));
        remain.setText(remainText + "");
        progress.setMax(Integer.parseInt((String) data.get("totalnum")));
        progress.setProgress(Integer.parseInt((String) data.get("curnum")));
        List<View> views = new ArrayList<>();
        String[] images = ((String) data.get("img")).split(",");
        for (int i = 0; i < images.length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
            SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.image_title);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context
                    .WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            image.setMaxWidth(displayMetrics.widthPixels);
            image.setMinimumWidth(displayMetrics.widthPixels);
            image.setMaxHeight(displayMetrics.widthPixels - 20);
            image.setMinimumHeight(displayMetrics.widthPixels - 20);
            image.setImageURI(Uri.parse(API.ADD + images[i]));
            views.add(view);
        }
        PagerAdapter pagerAdapter = new PagerAdapter(views);
        viewPager.setAdapter(pagerAdapter);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Message message = new Message();
                message.what = BackgroundTask.FINISH;
                Bundle bundle = new Bundle();

                if (number.getText() == null || number.getText().length() <= 0) {
                    number.setText("0");
                    bundle.putInt("number", 0);
                } else {
                    bundle.putInt("number", Integer.parseInt(number.getText().toString().trim()));
                }

                handler.sendMessage(message);
            }
        });

        navigation.setOnClickListener(new OnClick());
        push.setOnClickListener(new OnClick());
        add.setOnClickListener(new OnClick());
        share.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        viewPager = (ViewPager) rootView.findViewById(R.id.image_title);
        name = (TextView) rootView.findViewById(R.id.name);
        price = (TextView) rootView.findViewById(R.id.price);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
        total = (TextView) rootView.findViewById(R.id.total);
        current = (TextView) rootView.findViewById(R.id.current);
        remain = (TextView) rootView.findViewById(R.id.remain);
        push = (TextView) rootView.findViewById(R.id.push);
        number = (EditText) rootView.findViewById(R.id.number);
        add = (TextView) rootView.findViewById(R.id.add);
        share = (ImageView) rootView.findViewById(R.id.share);

        evaluateNumber = (TextView) rootView.findViewById(R.id.evaluateNumber);
        evaluationInfo = (TextView) rootView.findViewById(R.id.evaluateInfo);
        rating = (RatingBar) rootView.findViewById(R.id.rating);

        arrPing = (TextView) rootView.findViewById(R.id.arrping);//全部
        arrPing.setOnClickListener(new OnClick());
        historys=(TextView) rootView.findViewById(R.id.historys);//历史记录
        historys.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.historys://历史记录
                    Intent intents = new Intent(getActivity(), HistoryActivity.class);//需要用户id和商品id
                    intents.putExtra("cid", cid);
//                    intents.putExtra("yunp", true);

                    startActivity(intents);



                    break;

                case R.id.arrping://全部评论
                    Intent intent = new Intent(getActivity(), AllComments.class);//需要用户id和商品id
                    intent.putExtra("cid", cid);
                    intent.putExtra("yunp", true);

                    startActivity(intent);

                    break;
                case R.id.navigation: {
                    CloudDetailActivity.cloudDetailActivity.finish();
                }
                break;
                case R.id.push: {
                    int num = Integer.parseInt(number.getText().toString().trim());
                    if (num < 1 || num == 1) {

                    } else {
                        if (num > 1) {
                            num--;
                        }
                    }
                    number.setText(num + "");
                }
                break;
                case R.id.add: {

                    int num = Integer.parseInt(number.getText().toString().trim());
                    if(num+curNum==totalNum){
                        Toast.makeText(getContext(),"人数超过允许参与人数",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    num++;
                    number.setText(num + "");
                }
                break;
                case R.id.share: {
                    System.out.println("云购分享点击+++++++++++++++++++++++++++++");
                    showShare();
                }
                break;
            }
        }
    }

    public void showShare() {


        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE + "&uid=" + lod.get("USER_INFO", "UID", "'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context, shareAdd, handler);
        backgroundTask.doInBackground();

//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(getActivity(), appID, appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(), appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxCircleHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");

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
     mController.setShareMedia(new UMImage(getActivity(), "http://www.qkxmall.com/xiazai/"));
        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource(getResources(),
                R.drawable.fenxiang)));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(getActivity(), false);
    }

    private class ProductsEvaluation extends Handler {

        ProgressDialog progressDialog;

        public ProductsEvaluation(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        String result = bundle.getString("result");
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject data = jsonObject.getJSONObject("data");
//                            JSONArray rows = data.getJSONArray("comment");
                            JSONObject comment=data.getJSONObject("comment");


                            String s=comment.get("content").toString().trim();

                            System.out.println("！！！！！！！！！！！！！！ 最新的一条评论"+s);
                            if (!s.equals("")){
                                evaluationInfo.setText(s);
                            }

//                            if (rows.length() != 0)
//                                for (int i = 0; i < rows.length(); i++) {
//                                    if (i == 0) {
//                                        JSONObject object = rows.getJSONObject(i);
//                                        evaluationInfo.setText(object.getString("content"));
//                                    }
//                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (progressDialog.isShowing() && progressDialog != null) {
                        progressDialog.dismiss();
                    } else {
                        Log.e("ProgressDialog", "控件未初始化");
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    if (progressDialog.isShowing() && progressDialog != null) {
                        progressDialog.dismiss();
                    } else {
                        Log.e("ProgressDialog", "控件未初始化");
                    }
                }
                break;
            }
        }
    }


}
