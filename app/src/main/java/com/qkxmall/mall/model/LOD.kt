package com.qkxmall.mall.model

import android.content.Context

/**
 * Created by Sunshine on 1/6/2016.
 */
public class LOD{
    var context : Context

    constructor(context: Context) {
        this.context = context
    }

    public fun get(name: String, key: String, defValue:String): String {
        val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,defValue)
    }

}