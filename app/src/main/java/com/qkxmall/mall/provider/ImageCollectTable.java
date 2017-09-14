package com.qkxmall.mall.provider;

/**
 * Created by yuna on 2017/9/8.
 */

public class ImageCollectTable {
    public static final String TABLE_NAME = "imageCollect_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String KEY = "key";
    private int _id;

    private String name;

   private String key;

  public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
      this._id = _id;
    }

     public String getName() {
       return name;
    }

    public void setName(String name) {
        this.name = name;
   }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
     this.key = key;
    }
}

