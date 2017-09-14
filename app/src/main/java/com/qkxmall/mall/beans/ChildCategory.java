package com.qkxmall.mall.beans;

import java.util.List;

/**
 * Created by xingyuna on 2017/5/6.
 */

public class ChildCategory {

    private String id;
    private String name;
    private String thumb_image;
    private List<ChildItem> childitems;

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

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public List<ChildItem> getChilditems() {
        return childitems;
    }

    public void setChilditems(List<ChildItem> childitems) {
        this.childitems = childitems;
    }
}
