package com.qkxmall.mall.views.hui.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.FragmentPagerAdapter;
import com.qkxmall.mall.define.override.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HuiDetailSecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HuiDetailSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HuiDetailSecondFragment extends Fragment {
    private Context context;
    private static final String ARG_GID = "hid";
    private String GID;

    private View progressBar;
    private WebView webview;
    private boolean hasInited = false;
    TextView pic,text;

    private ViewPager detail = null;

    private String html;
    private String html1;
    private String html2;

    private OnFragmentInteractionListener mListener;

    public HuiDetailSecondFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            GID = getArguments().getString(ARG_GID);
            html = getArguments().getString("descript");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hui_detail_second, container, false);
        webview = (WebView) rootView.findViewById(R.id.fragment3_webview);
        progressBar = rootView.findViewById(R.id.progressbar);
        pic = (TextView) rootView.findViewById(R.id.pic);
        text = (TextView) rootView.findViewById(R.id.text);

        detail = (ViewPager) rootView.findViewById(R.id.detail);
        String[] temp = html.split("_ueditor_page_break_tag_");

        //Judge length of temp descript
        if (temp.length == 2) {
            html1 = temp[0];
            html2 = temp[1];
        }else {
            html1 = html2 = temp[0];
        }

        List<Fragment> list = new ArrayList<>();
        Detail1Fragment detail1Fragment = new Detail1Fragment();
        detail1Fragment.newInstance(context, html1);
        Detail2Fragment detail2Fragment = new Detail2Fragment();
        detail2Fragment.newInstance(context,html2);
        list.add(detail2Fragment);
        list.add(detail1Fragment);

        progressBar.setVisibility(View.GONE);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(),list);
        detail.setAdapter(fragmentPagerAdapter);

        pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
        pic.setTextColor(getResources().getColor(R.color.white));
        text.setTextColor(getResources().getColor(R.color.text_black));
        text.setBackgroundColor(getResources().getColor(R.color.white));

        pic.setOnClickListener(new OnClick());
        text.setOnClickListener(new OnClick());
        detail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (detail.getCurrentItem() == 0){
                    pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    pic.setTextColor(getResources().getColor(R.color.white));
                    text.setTextColor(getResources().getColor(R.color.text_black));
                    text.setBackgroundColor(getResources().getColor(R.color.white));
                }else {
                    text.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    text.setTextColor(getResources().getColor(R.color.white));
                    pic.setTextColor(getResources().getColor(R.color.text_black));
                    pic.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pic:
                    pic.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    pic.setTextColor(getResources().getColor(R.color.white));
                    text.setTextColor(getResources().getColor(R.color.text_black));
                    text.setBackgroundColor(getResources().getColor(R.color.white));
                    detail.setCurrentItem(0);
                    System.out.println("点击0");
                    break;
                case R.id.text:
                    text.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    text.setTextColor(getResources().getColor(R.color.white));
                    pic.setTextColor(getResources().getColor(R.color.text_black));
                    pic.setBackgroundColor(getResources().getColor(R.color.white));
                    detail.setCurrentItem(1);
                    System.out.println("点击1");
                    break;
            }
        }
    }

}
