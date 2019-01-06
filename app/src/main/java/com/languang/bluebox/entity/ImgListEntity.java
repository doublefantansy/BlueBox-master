package com.languang.bluebox.entity;

import java.io.Serializable;
import java.util.List;

public class ImgListEntity implements Serializable {
    private String time;
    private boolean checked = false;
    private List<ImgEntity> imgEntityList;

    public List<ImgEntity> getImgEntityList() {
        return imgEntityList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
