package com.qkxmall.mall.views.cart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.CloudCartBean;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sunshine on 01/17/2016.
 */
public class CloudCartAdapter extends BaseAdapter {
    Context context;
    Handler handler;
    ArrayList<CloudCartBean> rows;
    BitmapUtils utils;

    public CloudCartAdapter(Context context, Handler handler, ArrayList<CloudCartBean> rows) {
        this.context = context;
        this.handler = handler;
        this.rows = rows;
        utils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return rows == null ? 0 : rows.size();
    }

    @Override
    public CloudCartBean getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final A a;
        if (convertView != null) {
            a = (A) convertView.getTag();
        } else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_gouwuche_cloud_item_layout, null);
            a.check = (CheckBox) convertView.findViewById(R.id.check);
            a.image = (ImageView) convertView.findViewById(R.id.picture);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.total = (TextView) convertView.findViewById(R.id.total);
            a.remain = (TextView) convertView.findViewById(R.id.remain);
            a.current = (TextView) convertView.findViewById(R.id.current);
            a.progress = (ProgressBar) convertView.findViewById(R.id.progress);
            a.itemFrame = (LinearLayout) convertView.findViewById(R.id.item_frame);
            convertView.setTag(a);
        }
       final  CloudCartBean bean = getItem(position);

//        utils.display();
        Picasso.with(context)
                .load(Uri.parse("https://www.qkxmall.com" + bean.img))
//                        .placeholder(R.drawable.placeholder)
//                        .error(R.drawable.error)
//                        .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
//                        .centerInside()
//                                    .tag(this)
                .into(a.image);
        a.progress.setProgress(bean.curnum);
        a.name.setText(bean.name);
        a.total.setText(String.valueOf(bean.totalnum));
        a.current.setText(String.valueOf(bean.curnum));
        int remain = bean.totalnum - bean.curnum;
        a.remain.setText(remain + "");
        a.progress.setMax(bean.totalnum);
//        a.total.setText(bean.totalnum);

//        final Handler handler2 = new Handler() {
//
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case BackgroundTask.SUCCESS: {
//                        Bundle bundle = msg.getData();
//                        if (bundle != null) {
//                            try {
//                                JSONObject object = new JSONObject(bundle.getString("result"));
//                                System.out.println("！！！！！！！购物车数据 ：" + bundle.getString("result"));
//
//                                JSONObject json = object.optJSONObject("data");
//                                JSONObject jsonObject = null;
//
//                                if (json.has("cloud") && !TextUtils.isEmpty(json.getString("cloud"))) {
//                                    jsonObject = json.getJSONObject("cloud");
//                                }
//                                String[] img = null;
//                                if (json.has("img") && !TextUtils.isEmpty(json.getString("img"))) {
//                                    img = ((String) jsonObject.optString("img")).split(",");
//                                    utils.display(a.image, API.ADD + img[0]);
//                                }
////                                a.image.setImageURI(Uri.parse(API.ADD + img[0]));
//                                if (jsonObject != null) {
//                                    if (a.name != null)
//                                        a.name.setText(jsonObject.optString("name"));
//                                    if (!jsonObject.opt("totalnum").equals("null") && jsonObject.opt("totalnum") != null)
//                                        a.progress.setMax(Integer.parseInt((String) jsonObject.opt("totalnum")));
//                                    if (!jsonObject.opt("curnum").equals("null"))
//                                        a.progress.setProgress(Integer.parseInt((String) jsonObject.opt("curnum")));
//                                    a.total.setText((CharSequence) jsonObject.opt("totalnum"));
//                                    a.current.setText((CharSequence) jsonObject.opt("curnum"));
//                                    int remain = Integer.parseInt((String) jsonObject.opt("totalnum")) - Integer.parseInt((String) jsonObject.opt("curnum"));
//                                    a.remain.setText(remain + "");
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                                System.out.println("异常原因：" + e);
//                            }
//                        }
//                    }
//                    break;
//                    case BackgroundTask.FAILURE: {
//                        Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//            }
//        };
//        String url = API.CLOUD_GOODS_DETAIL_URL + "&cid=" + data.get("cid");
//        Log.e("ErrorString", url);
//        BackgroundTask backgroundTask = new BackgroundTask(context, url, handler2);
//        backgroundTask.doInBackground();
        a.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Message message = new Message();
                    message.what = BackgroundTask.CHECKED;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("id", bean);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!传递的数据  1:" + String.valueOf(position));
                } else {
                    Message message = new Message();
                    message.what = BackgroundTask.UN_CHECKED;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("id", bean);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!传递的数据  2:" + String.valueOf(position));
                }
            }
        });
//       a.check.setFocusable(false);
//        a.image.setFocusable(false);
//        a.name.setFocusable(false);
//        a.total.setFocusable(false);
//        a.remain.setFocusable(false);
//        a.current.setFocusable(false);
//        a.progress.setFocusable(false);
//        a.itemFrame.setFocusable(false);

        return convertView;
    }

    static class A {
        CheckBox check;
        ImageView image;
        TextView name;
        TextView total;
        TextView remain;
        TextView current;
        ProgressBar progress;
        LinearLayout itemFrame;
    }

}
