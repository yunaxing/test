package com.qkxmall.mall.model.type

import android.content.Context
import android.widget.TextView

/**
 * Created by Sunshine on 01/03/2016.
 */
public class ColorSet{
    var context: Context;

    constructor(context: Context) {
        this.context = context
    }


    operator fun set(textViews: Array<TextView>, selectPosition:Int, colorBgAfter: Int, colorBgBefore:Int, colorAfter: Int, colorBefore: Int) {
        for(i in 0..textViews.size -1){
            if(i == selectPosition){
                textViews[i].setTextColor(colorAfter)
                textViews[i].setBackgroundColor(colorBgAfter)
            }else{
                textViews[i].setTextColor(colorBefore)
                textViews[i].setBackgroundColor(colorBgBefore)
            }
        }
    }


}
