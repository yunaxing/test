package com.qkxmall.mall.views.fragment.giftCode;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Sunshine on 02/17/2016.
 */
public class Gift  {

    Context context;

    public Gift(Context context) {
        this.context = context;
    }

    public void format(ImageView imageView){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        imageView.setAdjustViewBounds(true);

        imageView.setMaxWidth(displayMetrics.widthPixels / 2);
        imageView.setMinimumWidth(displayMetrics.widthPixels / 2);

        imageView.setMaxHeight(((displayMetrics.widthPixels / 2) * 532) / 540);
        imageView.setMinimumHeight(((displayMetrics.widthPixels / 2) * 532) / 540);

        int screenWidth = windowManager.getDefaultDisplay().getWidth();

        imageView.setMaxHeight(((screenWidth / 2) * 532) / 540);
        imageView.setMinimumHeight(((screenWidth / 2) * 532) / 540);

        Log.e("Giftwidth", String.valueOf(displayMetrics.widthPixels));
        Log.e("GiftScreenWidth", String.valueOf(screenWidth));




    }

}
