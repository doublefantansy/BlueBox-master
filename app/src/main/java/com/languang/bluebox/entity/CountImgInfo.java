package com.languang.bluebox.entity;

public class CountImgInfo {

    /**
     * status : 9999
     * tagimg : {"videoCount":1,"size":90134033,"imageCount":34}
     * outimg : {"videoCount":0,"size":0,"imageCount":0}
     */

    private String status;
    private TagimgBean tagimg;
    private OutimgBean outimg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TagimgBean getTagimg() {
        return tagimg;
    }

    public void setTagimg(TagimgBean tagimg) {
        this.tagimg = tagimg;
    }

    public OutimgBean getOutimg() {
        return outimg;
    }

    public void setOutimg(OutimgBean outimg) {
        this.outimg = outimg;
    }

    public static class TagimgBean {
        /**
         * videoCount : 1
         * size : 90134033
         * imageCount : 34
         */

        private int videoCount;
        private int size;
        private int imageCount;

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getImageCount() {
            return imageCount;
        }

        public void setImageCount(int imageCount) {
            this.imageCount = imageCount;
        }
    }

    public static class OutimgBean {
        /**
         * videoCount : 0
         * size : 0
         * imageCount : 0
         */

        private int videoCount;
        private int size;
        private int imageCount;

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getImageCount() {
            return imageCount;
        }

        public void setImageCount(int imageCount) {
            this.imageCount = imageCount;
        }
    }
}
