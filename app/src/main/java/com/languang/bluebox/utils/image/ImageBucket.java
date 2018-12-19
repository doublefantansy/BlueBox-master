package com.languang.bluebox.utils.image;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 49829
 * @date 2018/4/16
 */

public class ImageBucket {
    /**
     * /相册名
     */
    private String bucketName;
    /**
     * /相册中图片列表
     */
    private List<ImageItem> imageList;
    /**
     * /已选择的图片列表
     */
    private List<ImageItem> selectedImageList = new ArrayList<>();

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public List<ImageItem> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageItem> imageList) {
        this.imageList = imageList;
    }

    public List<ImageItem> getSelectedImageList() {
        return selectedImageList;
    }

    public void setSelectedImageList(List<ImageItem> selectedImageList) {
        this.selectedImageList = selectedImageList;
    }
}
