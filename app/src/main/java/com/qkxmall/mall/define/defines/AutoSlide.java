package com.qkxmall.mall.define.defines;

import android.os.Handler;
import android.support.v4.view.ViewPager;

/**
 * Created by Sunshine on 10/7/2015.
 */
public class AutoSlide {
    int isss = 0 ;
    Handler handler = new Handler();
    ViewPager viewPager;
    int count;

    public AutoSlide(ViewPager viewPager,int count) {
        this.viewPager = viewPager;
        this.count = count;
    }

    public void slide(){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(isss);
            }
        };
        new Thread(){
            @Override
            public void run() {

                try {
                    while (true){
                        for (;isss<count ;isss++){
                            handler.post(runnable);
                            if (isss == count-1){
                                sleep(750);
                            }else {
                                sleep(2000);
                            }
                        }
                        isss--;
                        for (;isss>0 ;isss--){
                            handler.post(runnable);
                            if (isss == count-1){
                                sleep(750);
                            }else {
                                sleep(2000);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
