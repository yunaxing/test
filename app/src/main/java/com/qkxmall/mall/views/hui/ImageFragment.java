package com.qkxmall.mall.views.hui;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

/**
 * ViewHolder simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    String url;
    Context context;
    private SimpleDraweeView image = null;
//    private ImageView mImage=null;


    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(Context context ,String url){
        ImageFragment fragment = new ImageFragment();
        fragment.context = context;
        fragment.url = url;
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        init(rootView);
        image.setImageURI(Uri.parse(API.ADD+url));
//        Glide.with(context).load(API.ADD+url).into(mImage);
        return rootView;
    }

    private void init(View rootView) {
        image = (SimpleDraweeView) rootView.findViewById(R.id.image_title);
//        mImage=(ImageView)rootView.findViewById(R.id.image);
    }

}
