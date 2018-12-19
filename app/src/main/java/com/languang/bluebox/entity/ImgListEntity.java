package com.languang.bluebox.entity;

import java.io.Serializable;
import java.util.List;

public class ImgListEntity implements Serializable {
    private String time;
    private List<ImgEntity> imgEntityList;

    public List<ImgEntity> getImgEntityList() {
        return imgEntityList;
    }

    public void setImgEntityList(List<ImgEntity> imgEntityList) {
        this.imgEntityList = imgEntityList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
