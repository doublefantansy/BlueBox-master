package com.languang.bluebox.entity.facility;

import java.util.List;

/**
 * 硬盘信息
 *
 * @author 49829
 * @date 2018/4/9
 */

public class DiskInfo {

    /**
     * name : 硬盘SSD
     * storage : 250
     * used : 101.2
     * statistics : [{"name":"系统","storage":20,"color":"#ffffff"},{"name":"临时缩略文件","storage":10,"color":"#ffffff"},{"name":"图片片库索引","storage":30,"color":"#ffffff"},{"name":"待归档RAW文件","storage":50,"color":"#ffffff"}]
     */

    private String name;
    private int storage;
    private double used;
    private List<StatisticsBean> statistics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public List<StatisticsBean> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsBean> statistics) {
        this.statistics = statistics;
    }

    public static class StatisticsBean {
        /**
         * name : 系统
         * storage : 20
         * color : #ffffff
         */

        private String name;
        private int storage;
        private String color;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStorage() {
            return storage;
        }

        public void setStorage(int storage) {
            this.storage = storage;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
