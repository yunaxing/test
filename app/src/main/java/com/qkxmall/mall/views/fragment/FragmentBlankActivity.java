package com.qkxmall.mall.views.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.qkxmall.mall.R;

public class FragmentBlankActivity extends AppCompatActivity {

    public static FragmentBlankActivity fragmentBlankActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_blank);
        fragmentBlankActivity = this;
        if (savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ChangePhoneFragment changePhoneFragment = new ChangePhoneFragment();
            changePhoneFragment.init(getApplicationContext());
            fragmentTransaction.replace(R.id.fragment_blank, changePhoneFragment);
            fragmentTransaction.commit();
        }
    }

}
