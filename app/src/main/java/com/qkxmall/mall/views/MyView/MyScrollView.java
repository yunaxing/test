package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/4/19.
 */
public class MyScrollView extends    ScrollView {


    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context) {
        super(context);
    }

    private boolean bl;

    private float startX;
    private float startY;
    private float offsetX;
    private float offsetY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getChildAt(0);
        if (getScrollY() == 0) {
            bl = true;
            System.out.println("现在顶部bl：" + bl);
        }

        if (getScrollY() == (v.getHeight() - getHeight()) && bl == true) {
            getParent().requestDisallowInterceptTouchEvent(false);
            bl = false;
            System.out.println("现在底部bl：" + bl);
            scrollTo(0, v.getHeight() - getHeight());
        } else {
            if (bl) {
                getParent().requestDisallowInterceptTouchEvent(true);
                System.out.println("滑动中：" + bl);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }

        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //按下
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                offsetX = ev.getX() - startX;
                offsetY = ev.getY() - startY;
                if(Math.abs(offsetX) < Math.abs(offsetY)){//判断是否是垂直方向
                   if(offsetY>-5){
                       getParent().requestDisallowInterceptTouchEvent(true);
                       System.out.println("向下滑动");
                   }else if(offsetY>5&&getScrollY() == (v.getHeight() - getHeight())){
                       getParent().requestDisallowInterceptTouchEvent(true);
                       System.out.println("向上滑动");
                   }

                }



                break;
        }


        return super.dispatchTouchEvent(ev);
    }
}
