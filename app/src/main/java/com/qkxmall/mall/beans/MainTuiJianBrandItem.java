package com.qkxmall.mall.beans;

import java.util.ArrayList;

/**
 * Created by yuna on 2017/9/7.
 */

public class MainTuiJianBrandItem {
    private String id;
    private String name;
    private String logo;
    private ArrayList<GoodListItem> goodListItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<GoodListItem> getGoodListItems() {
        return goodListItems;
    }

    public void setGoodListItems(ArrayList<GoodListItem> goodListItems) {
        this.goodListItems = goodListItems;
    }
}
