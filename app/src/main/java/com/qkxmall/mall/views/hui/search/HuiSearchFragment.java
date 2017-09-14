package com.qkxmall.mall.views.hui.search;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link HuiSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HuiSearchFragment extends Fragment {
    Context context;

    private ImageView navigation = null;
    private EditText info = null;
    private Button search = null;


    public HuiSearchFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hui_search,container,false);
        init(rootView);

        navigation.setOnClickListener(new OnClick());
        search.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.navigation);
        info = (EditText) rootView.findViewById(R.id.info);
        search = (Button) rootView.findViewById(R.id.search);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    HuiSearchActivity.huiSearchActivity.finish();
                }
                    break;
                case R.id.search:{
                    if (! info.getText().toString().trim().equals("")|| info.getText().toString().trim() != null){
                        Intent intent = new Intent(context, HuiClassItemsActivity.class);
                        intent.putExtra("keyword",info.getText().toString().trim());
                        startActivity(intent);
                    }else {
                        Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }
}
