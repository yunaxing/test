package com.qkxmall.mall.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangjinxue on 2017/1/22.
 */
public class CloudCartBean implements Parcelable {
    /**
     * data.put("id", jsonObject.get("id"));
     * data.put("uid", jsonObject.get("uid"));
     * data.put("cid", jsonObject.get("cid"));
     * data.put("name",jsonObject.getString("name"))
     * data.put("num", jsonObject.get("num"));
     * data.put("c_price", jsonObject.get("c_price"));
     * data.put("posttime", jsonObject.get("posttime"));
     * data.put("img",jsonObject.getString("img"));
     */
    public String id;
    public String uid;
    public String cid;
    public String name;
    public int num;
    public String posttime;
    public String c_price;
    public String img;
    public int curnum;
    public int totalnum;
    public boolean isChecked;


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        CloudCartBean other = (CloudCartBean) o;

        if (other.id.equals(this.id)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode() % 10;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.uid);
        dest.writeString(this.cid);
        dest.writeString(this.name);
        dest.writeInt(this.num);
        dest.writeString(this.posttime);
        dest.writeString(this.c_price);
        dest.writeString(this.img);
        dest.writeInt(this.curnum);
        dest.writeInt(this.totalnum);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public CloudCartBean() {
    }

    protected CloudCartBean(Parcel in) {
        this.id = in.readString();
        this.uid = in.readString();
        this.cid = in.readString();
        this.name = in.readString();
        this.num = in.readInt();
        this.posttime = in.readString();
        this.c_price = in.readString();
        this.img = in.readString();
        this.curnum = in.readInt();
        this.totalnum = in.readInt();
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CloudCartBean> CREATOR = new Parcelable.Creator<CloudCartBean>() {
        @Override
        public CloudCartBean createFromParcel(Parcel source) {
            return new CloudCartBean(source);
        }

        @Override
        public CloudCartBean[] newArray(int size) {
            return new CloudCartBean[size];
        }
    };
}
