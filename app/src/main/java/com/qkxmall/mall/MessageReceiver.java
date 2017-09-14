package com.qkxmall.mall;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qkxmall.mall.model.MessageBean;
import com.qkxmall.mall.views.CommonWebviewActivity;
import com.qkxmall.mall.views.PopuwindowActivity;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;
import com.qkxmall.mall.views.user.PerCenterMyWalletActivity;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * Created by Administrator on 2016/12/31.
 */
public class MessageReceiver extends XGPushBaseReceiver {

    public static final String LogTag = "TPushReceiver";

    private void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    // 通知展示
    @Override
    public void onNotifactionShowedResult(Context context,
                                          XGPushShowedResult notifiShowedRlt) {
        if (context == null || notifiShowedRlt == null) {
            return;
        }

        String text = notifiShowedRlt.getCustomContent();
//        if (TextUtils.isEmpty(text))
//            return;
//        handleContent(context, text);

//        show(context, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "反注册成功";
        } else {
            text = "反注册失败" + errorCode;
        }
        Log.d(LogTag, text);
        show(context, text);

    }

    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"设置成功";
        } else {
            text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
        }
        Log.d(LogTag, text);
        show(context, text);

    }

    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"删除成功";
        } else {
            text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
        }
        Log.d(LogTag, text);
        show(context, text);

    }

    // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
    @Override
    public void onNotifactionClickedResult(Context context,
                                           XGPushClickedResult message) {
        if (context == null || message == null) {
            return;
        }
        String text = message.getCustomContent();

//        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
//            // 通知在通知栏被点击啦。。。。。
//            // APP自己处理点击的相关动作
//            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
//            text = "通知被打开 :" + message;
//        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
//            // 通知被清除啦。。。。
//            // APP自己处理通知被清除后的相关动作
//            text = "通知被清除 :" + message;
//        }
//        Toast.makeText(context, "广播接收到通知被点击:" + message.toString(),
//                Toast.LENGTH_SHORT).show();
//        if (TextUtils.isEmpty(text))
//            return;
//        handleContent(context, text);
    }

    private void handleContent(Context context, String content) {

        Intent intent = null;
        try {
            Gson gson = new Gson();
            MessageBean bean = gson.fromJson(content, MessageBean.class);
            intent = new Intent();
            if ("0001".equals(bean.type)) {
                intent = new Intent(context, HuiClassItemsActivity.class);
                intent.putExtra("categoryTitle", "惠");
                intent.putExtra("item_type", "hg_ad");
                intent.putExtra("category_id", bean.vid);

            } else if ("0002".equals(bean.type)) {
                intent = new Intent(context, HuiClassItemsActivity.class);
                intent.putExtra("brand_id", bean.vid);
                intent.putExtra("categoryTitle", "品牌");

            } else if ("0003".equals(bean.type)) {
//                intent = new Intent(context, AllCloudClassItem
//                        .class);
//                intent.putExtra("categoryTitle", "云");
//
//                //item_type 广告
//                intent.putExtra("category_id", bean.vid);
//                intent.putExtra("item_type", "ad");
                intent = new Intent (context, PopuwindowActivity.class);


            } else if ("0004".equals(bean.type)) {
                intent = new Intent(context, AllCloudClassItem
                        .class);
                intent.putExtra("categoryTitle", "即将揭晓");
                intent.putExtra("category_id", "0");

            } else if ("0005".equals(bean.type)) {
                intent = new Intent(context, PerCenterMyWalletActivity.class);
            }
            notification(bean.title,bean.content,context,intent);
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }

        // APP自主处理的过程。。。

//        show(context, text);
    }

    @Override
    public void onRegisterResult(Context context, int errorCode,
                                 XGPushRegisterResult message) {

        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = message + "注册成功";
            // 在这里拿token
            String token = message.getToken();
        } else {
            text = message + "注册失败，错误码：" + errorCode;
        }
        Log.d(LogTag, text);
//        show(context, text);
    }
    void notification(String title, String content, Context context, Intent resultIntent) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_lun)
                        .setContentTitle(title)
                        .setContentText(content);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        Notification n = mBuilder.build();
        n.flags = Notification.FLAG_AUTO_CANCEL;


        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, n);
    }

    // 消息透传
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        if (context == null || message == null) {
            return;
        }
        String text = message.getCustomContent();
        if (TextUtils.isEmpty(text)) {
//            return;
            text=message.getContent();
        }
        if (TextUtils.isEmpty(text)) {
            return;
        }
        // TODO Auto-generated method stub
        handleContent(context, text);
    }
}
