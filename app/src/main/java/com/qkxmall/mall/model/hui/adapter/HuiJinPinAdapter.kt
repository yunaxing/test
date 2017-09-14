package com.qkxmall.mall.model.hui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.qkxmall.mall.R
import com.qkxmall.mall.nets.API
import java.util.*

/**
 * Created by Sunshine on 01/03/2016.
 */
public class HuiJinPinAdapter : BaseAdapter{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        var a:A
        if(convertView != null) {
            a = convertView.tag as A
        }else{
            a = A()
            convertView = LayoutInflater.from(context).inflate(R.layout.child_hui_jin_pin_item,null);
            a.image = convertView.findViewById(R.id.image_title) as SimpleDraweeView
            a.name = convertView.findViewById(R.id.name) as TextView
            a.price = convertView.findViewById(R.id.price) as TextView
            a.saled = convertView.findViewById(R.id.saled) as TextView
            a.kaiXinDou = convertView.findViewById(R.id.kaiXinDouNumber) as TextView
            convertView.tag = a
        }
        var data: HashMap<String ,Any>  = list.get(position)
        var imagesArray = ( data["thumb"] as String ).split(",")
        a.image.setImageURI(Uri.parse(API.ADD+imagesArray[0]))
        a.name.text = data["name"] as String
        a.price.text = data["shop_price"] as String
        a.kaiXinDou.text = data["kaixindou"] as String
        a.saled.text = data["sales_number"] as String
        return convertView
    }

    override fun getItem(position: Int): Any? {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        var position: String  = "$position"
        return java.lang.Long.parseLong(position)
    }

    override fun getCount(): Int {
        return list.size
    }

    var context: Context
    var list: List<HashMap<String ,Any>> = ArrayList()

    constructor(context: Context, list: List<HashMap<String, Any>>) : super() {
        this.context = context
        this.list = list
    }

    internal class A{
        lateinit var image: SimpleDraweeView
        lateinit var name: TextView
        lateinit var price: TextView
        lateinit var kaiXinDou: TextView
        lateinit var saled: TextView
    }

}
