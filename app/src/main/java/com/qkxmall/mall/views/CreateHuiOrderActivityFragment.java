package com.qkxmall.mall.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qkxmall.mall.R;

/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class CreateHuiOrderActivityFragment extends Fragment {
    Context context;
    //title widget

    public CreateHuiOrderActivityFragment() {
    }
    public void newInstance(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_creat_order, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {

    }
}
