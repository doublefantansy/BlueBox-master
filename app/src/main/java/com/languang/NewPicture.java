package com.languang;

import com.languang.bluebox.entity.ImgEntity;

import java.util.List;
import java.util.Map;

public class NewPicture {
    /**
     * status : 9999
     * count : 8
     * page : 0,60|0,60
     * files :
     */
    private String status;
    private String count;
    private String page;
    private Map<String, Map<String, ImgEntity>> files;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, Map<String, ImgEntity>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Map<String, ImgEntity>> files) {
        this.files = files;
    }
    /**
     * status : 9999
     * count : 8
     * page : 0,60|0,60
     * files : {"2018-11-06":{"B12B6C61-EC73-BB43-039B-7D8EE9463A25":{"idimg":"57","uuid":"B12B6C61-EC73-BB43-039B-7D8EE9463A25","srctype":"raw","smallname":"small_1544606151_6576.jpg","smallpath":"uploads/small/20181212/","srcname":"IMG_0020_PNG.png","srcpath":"uploads/raw/20181106/","srcmd5":"b12b6c61ec73bb43039b7d8ee9463a25","shottime":"2018-11-06 20:08:39","imgpixel":"16382,3628","imgsize":"3953864","insafe":"0","createtime":"2018-12-12 17:15:51","updatetime":"2018-12-12 17:15:51","ininfo":"0","indel":"0","cdate":"2018-11-06"}},"2018-12-12":{"6E698F7E-5BFF-CFE3-CE5D-F530F36A935D":{"idimg":"56","uuid":"6E698F7E-5BFF-CFE3-CE5D-F530F36A935D","srctype":"raw","smallname":"small_1544606136_9180.jpg","smallpath":"uploads/small/20181212/","srcname":"IMG_0021_JPG.png","srcpath":"uploads/raw/20181212/","srcmd5":"6e698f7e5bffcfe3ce5df530f36a935d","shottime":"2018-12-12 17:15:36","imgpixel":"645,645","imgsize":"57078","insafe":"0","createtime":"2018-12-12 17:15:36","updatetime":"2018-12-12 17:15:36","ininfo":"0","indel":"0","cdate":"2018-12-12"},"2EBBEAD1-BFD6-19DC-2940-96396C615ED5":{"idimg":"55","uuid":"2EBBEAD1-BFD6-19DC-2940-96396C615ED5","srctype":"raw","smallname":"small_1544606113_3341.jpg","smallpath":"uploads/small/20181212/","srcname":"IMG_0022_JPG.png","srcpath":"uploads/raw/20181212/","srcmd5":"2ebbead1bfd619dc294096396c615ed5","shottime":"2018-12-12 17:15:13","imgpixel":"675,675","imgsize":"59732","insafe":"0","createtime":"2018-12-12 17:15:13","updatetime":"2018-12-12 17:15:13","ininfo":"0","indel":"0","cdate":"2018-12-12"}},"2018-11-25":{"59EC2B83-0F58-20C8-52F1-E5DEF7935CDB":{"idimg":"54","uuid":"59EC2B83-0F58-20C8-52F1-E5DEF7935CDB","srctype":"raw","smallname":"small_1543203866_6361.jpg","smallpath":"uploads/small/20181126/","srcname":"IMG_0650_HEIC.png","srcpath":"uploads/raw/20181125/","srcmd5":"59ec2b830f5820c852f1e5def7935cdb","shottime":"2018-11-25 17:06:02","imgpixel":"4032,3024","imgsize":"1615945","insafe":"0","createtime":"2018-11-26 11:44:26","updatetime":"2018-11-26 11:44:26","ininfo":"0","indel":"0","cdate":"2018-11-25"}},"2018-11-22":{"ACC45AC0-CA83-8605-CA6A-440EC78560F4":{"idimg":"51","uuid":"ACC45AC0-CA83-8605-CA6A-440EC78560F4","srctype":"raw","smallname":"small_1542885159_2530.jpg","smallpath":"uploads/small/20181122/","srcname":"IMG_0585_JPG.png","srcpath":"uploads/raw/20181122/","srcmd5":"acc45ac0ca838605ca6a440ec78560f4","shottime":"2018-11-22 19:12:39","imgpixel":"1440,1080","imgsize":"195350","insafe":"0","createtime":"2018-11-22 19:12:39","updatetime":"2018-11-22 19:12:39","ininfo":"0","indel":"0","cdate":"2018-11-22"}},"2018-11-14":{"23BA014F-9FB3-6D3F-8657-07A86D01E4E2":{"idimg":"42","uuid":"23BA014F-9FB3-6D3F-8657-07A86D01E4E2","srctype":"raw","smallname":"small_1542187699_9040.jpg","smallpath":"uploads/small/20181114/","srcname":"IMG_0358_JPG.png","srcpath":"uploads/raw/20181114/","srcmd5":"23ba014f9fb36d3f865707a86d01e4e2","shottime":"2018-11-14 17:28:19","imgpixel":"1920,1080","imgsize":"444482","insafe":"0","createtime":"2018-11-14 17:28:19","updatetime":"2018-11-14 17:28:19","ininfo":"0","indel":"0","cdate":"2018-11-14"},"F65EB0DE-23B2-D02B-2D81-4BE649511877":{"idimg":"40","uuid":"F65EB0DE-23B2-D02B-2D81-4BE649511877","srctype":"raw","smallname":"small_1542187698_8671.jpg","smallpath":"uploads/small/20181114/","srcname":"IMG_0371_JPG.png","srcpath":"uploads/raw/20181114/","srcmd5":"f65eb0de23b2d02b2d814be649511877","shottime":"2018-11-14 17:28:18","imgpixel":"1080,1440","imgsize":"222655","insafe":"0","createtime":"2018-11-14 17:28:18","updatetime":"2018-11-14 17:28:18","ininfo":"0","indel":"0","cdate":"2018-11-14"}},"2009-10-18":{"F0B4CCF2-D5AB-FF7A-565C-E4BE168F5F1C":{"idimg":"21","uuid":"F0B4CCF2-D5AB-FF7A-565C-E4BE168F5F1C","srctype":"raw","smallname":"small_1541259413_6720.jpg","smallpath":"uploads/small/20181103/","srcname":"1541259413_6720.png","srcpath":"uploads/raw/20181103/","srcmd5":"f0b4ccf2d5abff7a565ce4be168f5f1c","shottime":"2009-10-18 16:49:28","imgpixel":"4288,2848","imgsize":"2581617","insafe":"0","createtime":"2018-11-03 23:36:53","updatetime":"2018-11-03 23:36:53","ininfo":"0","indel":"0","cdate":"2009-10-18"}}}
     */
}
