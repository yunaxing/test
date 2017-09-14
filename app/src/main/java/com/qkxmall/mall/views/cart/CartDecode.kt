package com.qkxmall.mall.views.cart

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by Sunshine on 1/6/2016.
 */
public class CartDecode{
    var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun hui(result: String): List<HashMap<String, Any>> {
        var list: List<HashMap<String ,Any>> = ArrayList<HashMap<String ,Any>>()
        var jsonObject: JSONObject = JSONObject(result)
        var jsonArray: JSONArray = jsonObject.getJSONArray("")
        for (i in 0..jsonArray.length()) {
            var data: HashMap<String ,Any> = HashMap()



//            list.add(data)
        }

        return list
    }

}