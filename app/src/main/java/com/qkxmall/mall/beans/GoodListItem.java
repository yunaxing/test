package com.qkxmall.mall.beans;

/**
 * Created by yuna on 2017/9/7.
 */

public class GoodListItem {
    private String id;
    private String name;
    private String smallPic;
    private String price;
    private String salednum;

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

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalednum() {
        return salednum;
    }

    public void setSalednum(String salednum) {
        this.salednum = salednum;
    }
}
