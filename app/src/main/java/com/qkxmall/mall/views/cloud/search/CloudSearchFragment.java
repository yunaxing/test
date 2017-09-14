package com.qkxmall.mall.views.cloud.search;

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
import com.qkxmall.mall.views.cloud.AllCloudClassItem;

public class CloudSearchFragment extends Fragment {
    Context context;

    private ImageView navigation = null;
    private EditText info = null;
    private Button search = null;

    public CloudSearchFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cloud_search, container, false);
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
                    CloudSearchActivity.cloudSearchActivity.finish();
                }
                    break;
                case R.id.search:{
                    if (!info.getText().toString().trim().equals("")){
                        Intent intent = new Intent(context, AllCloudClassItem.class);
                        intent.putExtra("keyword",info.getText().toString().trim());
                        intent.putExtra("category_id","999");
                        startActivity(intent);
                    }else {
                        Toast.makeText(context, "请输入搜索信息", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }
}
