package com.qkxmall.mall.define.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Sunshine on 8/22/2015.
 */
public class GridViewWithScrollView extends GridView {
    public GridViewWithScrollView(Context context) {
        super(context);
    }

    public GridViewWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }




}
