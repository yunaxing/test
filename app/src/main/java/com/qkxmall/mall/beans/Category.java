package com.qkxmall.mall.beans;

import java.util.ArrayList;

/**
 * Created by xingyuna on 2017/5/6.
 */

public class Category {
    private String id;
    private String name;
    private ArrayList<ChildCategory> chidCategorys;

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

    public ArrayList<ChildCategory> getChidCategorys() {
        return chidCategorys;
    }

    public void setChidCategorys(ArrayList<ChildCategory> chidCategorys) {
        this.chidCategorys = chidCategorys;
    }
}
