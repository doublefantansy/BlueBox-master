package com.languang.bluebox.utils.image;

/**
 * @author 49829
 * @date 2018/4/16
 */

public class ImageItem {
    /**
     * 图像ID
     */
    private String imageId;
    /**
     * 图像路径
     */
    private String imagePath;
    /**
     * 是否被选择
     */
    private boolean isSelected = false;
    /**
     * 持有一个相册类引用
     */
    private ImageBucket bucket;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ImageBucket getBucket() {
        return bucket;
    }

    public void setBucket(ImageBucket bucket) {
        this.bucket = bucket;
    }
}
