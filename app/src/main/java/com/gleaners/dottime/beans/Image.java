package com.gleaners.dottime.beans;

/**
 * @author ...
 * @date 2021-07-16 16:44
 * description：
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class Image implements Parcelable {

    private String path;
    private long time;
    private String name;

    public Image(String path, long time, String name){
        this.path = path;
        this.time = time;
        this.name = name;
    }

    protected Image(Parcel in) {
        path = in.readString();
        time = in.readLong();
        name = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeLong(time);
        dest.writeString(name);
    }
}
