package com.qkxmall.mall.views.child;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link AdvertisementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvertisementFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_URL = "url";

    // TODO: Rename and change types of parameters
    private String url;


    public AdvertisementFragment() {
        // Required empty public constructor
    }
    public static AdvertisementFragment newInstance(Context context, String url) {
        AdvertisementFragment fragment = new AdvertisementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_advertisement, container, false);
        SimpleDraweeView image = (SimpleDraweeView) rootView.findViewById(R.id.image_title);
        Log.e("ImageAddress",API.ADD+url);
        image.setImageURI(Uri.parse(API.ADD+url));
        return rootView;
    }

}
