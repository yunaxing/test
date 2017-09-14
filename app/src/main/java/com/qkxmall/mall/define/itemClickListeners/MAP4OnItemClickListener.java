package com.qkxmall.mall.define.itemClickListeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.qkxmall.mall.views.hui.container.HuiActivity;


/**
 * Created by Sunshine on 10/9/2015.
 */
public class MAP4OnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
    Context context;

    public MAP4OnItemClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, HuiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
