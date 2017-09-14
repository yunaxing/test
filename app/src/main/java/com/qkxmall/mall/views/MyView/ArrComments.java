package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.qkxmall.mall.model.LOD;
import android.widget.ImageView;

import com.qkxmall.mall.R;

/**
 * Created by Administrator on 2016/5/11. 所有评论
 */
public class ArrComments extends Activity{
      ImageView image;//返回按钮
      WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arr_commments_xm);
        init();
        //返回按钮
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LOD lod = new LOD(this);
        String id= lod.get("USER_INFO", "UID", "'");
        String gid="";
        String url="";
      //web属性设置
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        if (getIntent().getBooleanExtra("yunp",false)){//云购评论
              gid=getIntent().getStringExtra("cid");
             url="http://www.qkxmall.com/index.php?m=qkx&c=apipage&a=cloudcomm"+"&cid="+gid;
            System.out.println("！！！！！！！！！！！ url：" + url);
        }else{
            gid=getIntent().getStringExtra("gid");
            url="http://www.qkxmall.com/index.php?m=qkx&c=apipage&a=huicomm"+"&gid="+gid;
            System.out.println("！！！！！！！！！！！ url：" + url);

        }
        settings.setJavaScriptEnabled(true);// 表支持js
        webView.loadUrl(url);



    }
    public void init(){
        image= (ImageView) findViewById(R.id.arr_del);
        webView= (WebView) findViewById(R.id.arr_wab);
    }

}
