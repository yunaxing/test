package com.qkxmall.mall.views.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.views.cloud.CloudCommunityPageActivity;
import com.qkxmall.mall.views.hui.HuiCommunityPageActivity;
import com.qkxmall.mall.views.ReportPageActivity;
import com.qkxmall.mall.views.SuggestPageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CommunityFragment# } factory method to
 * create an instance of this fragment.
 */
public class CommunityFragment extends Fragment {

    private LinearLayout cloudPrefecture = null;
    private LinearLayout tabCommunityHuiPrefcture = null;
    private LinearLayout tabCommunitySuggest = null;
    private LinearLayout tabCommunityReport = null;
    private ScrollListView sheQuList = null;

    Context context;

    public CommunityFragment() {
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        init(rootView);



        List listSheQu = new ArrayList();
        Map<String ,Object> map1 = new HashMap<>();
        map1.put("title","穷开心商城的由来：");
        map1.put("msg","穷开心商城创立于2015年，是有张三带着自己的团队（XX ，XX）");
        Map<String ,Object> map2 = new HashMap<>();
        map2.put("title","摇奖方式的公平、公正、公开：");
        map2.put("msg", "穷开心商城中“云购”部分有摇奖过程，其摇奖方式采用的是赵四自主 ");
        Map<String ,Object> map3 = new HashMap<>();
        map3.put("title","穷开心商城的优势  品质、价格、服务：");
        map3.put("msg", "穷开心商城之所以能够在目前电商混战的时刻挺身而出，是凭借独立");
        listSheQu.add(map1);
        listSheQu.add(map2);
        listSheQu.add(map3);
        SimpleAdapter simpleAdapterListShequ = new SimpleAdapter(context
                ,listSheQu
                ,R.layout.she_qu_list_layout
                ,new String[]{"title","msg"}
                ,new int[]{R.id.list_item_title,R.id.list_item_msg});
        sheQuList.setAdapter(simpleAdapterListShequ);

        cloudPrefecture.setOnClickListener(new OnClickListeners());
        tabCommunityHuiPrefcture.setOnClickListener(new OnClickListeners());
        tabCommunityReport.setOnClickListener(new OnClickListeners());
        tabCommunitySuggest.setOnClickListener(new OnClickListeners());

        return rootView;
    }

    private void init(View rootView) {
        cloudPrefecture = (LinearLayout) rootView.findViewById(R.id.cloud_prefecture);
        tabCommunityHuiPrefcture = (LinearLayout) rootView.findViewById(R.id.tab_community_hui_prefecture);
        tabCommunitySuggest = (LinearLayout) rootView.findViewById(R.id.tab_community_suggest);
        tabCommunityReport = (LinearLayout) rootView.findViewById(R.id.tab_community_report);
        sheQuList = (ScrollListView) rootView.findViewById(R.id.shequ_list);
    }


    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cloud_prefecture:
                    Intent intentCommunityToCloud = new Intent(context,CloudCommunityPageActivity.class);
                    startActivity(intentCommunityToCloud);
                    break;
                case R.id.tab_community_hui_prefecture:
                    startActivity(new Intent(context,HuiCommunityPageActivity.class));
                    break;
                case R.id.tab_community_suggest:
                    startActivity(new Intent(context,SuggestPageActivity.class));
                    break;
                case R.id.tab_community_report:
                    startActivity(new Intent(context,ReportPageActivity.class));
                    break;
            }
        }
    }
}
