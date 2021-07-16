package com.gleaners.dottime.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @author ...
 * @date 2021-07-16 16:43
 * description：
 */
public class Folder implements Parcelable {

    private String name;
    private ArrayList<Image> images;

    public Folder(String name){
        this.name = name;
    }

    public Folder(String name, ArrayList<Image> images){
        this.name = name;
        this.images = images;
    }

    private Folder(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel in) {
            return new Folder(in);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public void addImage(Image image){
        if (image != null || image.equals("")){
            if (images == null){
                images = new ArrayList<>();
            }
            images.add(image);
        }
    }

    private static void getFolderName(String path){
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
