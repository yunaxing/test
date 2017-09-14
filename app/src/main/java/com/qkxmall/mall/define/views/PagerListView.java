package com.qkxmall.mall.define.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Sunshine on 8/24/2015.
 */
public class PagerListView extends ListView {
    public PagerListView(Context context) {
        super(context);
    }

    public PagerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }





}
