package com.qkxmall.mall.define.views;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.views.fragment.FragmentMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class XiangDaoActivity extends AppCompatActivity {
    ViewPager viewPager = null;
    ImageView[] circles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_dao);

        circles=new ImageView[3];
        circles[0]=(ImageView)findViewById(R.id.circle0);
        circles[1]=(ImageView)findViewById(R.id.circle1);
        circles[2]=(ImageView)findViewById(R.id.circle2);
        viewPager = (ViewPager) findViewById(R.id.vp);
        circles[0].setBackgroundResource(R.drawable.circle_press);
        List<View> views = new ArrayList<>();
        int[] images = new int[]{R.drawable.newjiaocheng1,R.drawable.newjiaocheng2,R.drawable.newjiaocheng3};
        for (int i = 0 ; i< 3 ;i++) {
            View view = LayoutInflater.from(XiangDaoActivity.this).inflate(R.layout.child_vp_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_title);
            imageView.setImageResource(images[i]);
            views.add(view);
        }
        PagerAdapter pagerAdapter = new PagerAdapter(XiangDaoActivity.this,viewPager,views);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<circles.length;i++){
                    if(position==i){
                        circles[i].setBackgroundResource(R.drawable.circle_press);
                    }else{
                        circles[i].setBackgroundResource(R.drawable.circle_normal);
                    }
                }
                if (position == 2) {
                    final Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(new Intent(XiangDaoActivity.this, FragmentMainActivity.class));
                            timer.cancel();
                            XiangDaoActivity.this.finish();
                            //以下是添加代码
                            getSharedPreferences("USER_INFO",MODE_PRIVATE).edit().putBoolean("isFristEnter",false).commit();
                        }
                    };
                    timer.schedule(timerTask,2000);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getChildCount() == 2) {
                    startActivity(new Intent(XiangDaoActivity.this, FragmentMainActivity.class));
                    XiangDaoActivity.this.finish();
                }
            }
        });
    }
}
