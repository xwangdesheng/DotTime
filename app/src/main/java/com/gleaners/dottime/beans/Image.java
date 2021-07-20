package com.gleaners.dottime.beans;

/**
 * @author ...
 * @date 2021-07-16 16:44
 * description：
 */


import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class Image implements Serializable {

    private String path;
    private long time;
    private String name;

    public Image(String path, long time, String name){
        this.path = path;
        this.time = time;
        this.name = name;
    }

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
}
