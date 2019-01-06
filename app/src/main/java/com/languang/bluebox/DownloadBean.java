package com.languang.bluebox;

import java.util.Map;

public class DownloadBean {
    /**
     * status : 9999
     * count : 1
     * msg : 迁出文件列表如下
     * files : {"C46C5F37-9451-FCC7-65E9-AEA33A44BEB3":{"idimg":"95","uuid":"C46C5F37-9451-FCC7-65E9-AEA33A44BEB3","srctype":"raw","smallname":"small_1545920742_2764.jpg","smallpath":"uploads/small/20181227/","srcname":"IMG_0018_PNG.png","srcpath":"uploads/raw/20181227/","srcmd5":"c46c5f379451fcc765e9aea33a44beb3","shottime":"2018-12-27 22:25:42","imgpixel":"960,936","imgsize":"82584","insafe":"0","createtime":"2018-12-27 22:25:42","updatetime":"2018-12-27 22:31:19","ininfo":"1","indel":"0","cdate":"2018-12-27","idop":"153","opcreatetime":"2019-01-02 14:51:05","opuploadtime":null,"opstate":"0","optype":"3"}}
     */
    private String status;
    private int count;
    private String msg;
    private Map<String, FilesBean> files;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, FilesBean> getFiles() {
        return files;
    }

    public void setFiles(Map<String, FilesBean> files) {
        this.files = files;
    }

    public class FilesBean {
        /**
         * idimg : 95
         * uuid : C46C5F37-9451-FCC7-65E9-AEA33A44BEB3
         * srctype : raw
         * smallname : small_1545920742_2764.jpg
         * smallpath : uploads/small/20181227/
         * srcname : IMG_0018_PNG.png
         * srcpath : uploads/raw/20181227/
         * srcmd5 : c46c5f379451fcc765e9aea33a44beb3
         * shottime : 2018-12-27 22:25:42
         * imgpixel : 960,936
         * imgsize : 82584
         * insafe : 0
         * createtime : 2018-12-27 22:25:42
         * updatetime : 2018-12-27 22:31:19
         * ininfo : 1
         * indel : 0
         * cdate : 2018-12-27
         * idop : 153
         * opcreatetime : 2019-01-02 14:51:05
         * opuploadtime : null
         * opstate : 0
         * optype : 3
         */
        private String idimg;
        private String uuid;
        private String srctype;
        private String smallname;
        private String smallpath;
        private String srcname;
        private String srcpath;
        private String srcmd5;
        private String shottime;
        private String imgpixel;
        private String imgsize;
        private String insafe;
        private String createtime;
        private String updatetime;
        private String ininfo;
        private String indel;
        private String cdate;
        private String idop;
        private String opcreatetime;
        private Object opuploadtime;
        private String opstate;
        private String optype;

        public String getIdimg() {
            return idimg;
        }

        public void setIdimg(String idimg) {
            this.idimg = idimg;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getSrctype() {
            return srctype;
        }

        public void setSrctype(String srctype) {
            this.srctype = srctype;
        }

        public String getSmallname() {
            return smallname;
        }

        public void setSmallname(String smallname) {
            this.smallname = smallname;
        }

        public String getSmallpath() {
            return smallpath;
        }

        public void setSmallpath(String smallpath) {
            this.smallpath = smallpath;
        }

        public String getSrcname() {
            return srcname;
        }

        public void setSrcname(String srcname) {
            this.srcname = srcname;
        }

        public String getSrcpath() {
            return srcpath;
        }

        public void setSrcpath(String srcpath) {
            this.srcpath = srcpath;
        }

        public String getSrcmd5() {
            return srcmd5;
        }

        public void setSrcmd5(String srcmd5) {
            this.srcmd5 = srcmd5;
        }

        public String getShottime() {
            return shottime;
        }

        public void setShottime(String shottime) {
            this.shottime = shottime;
        }

        public String getImgpixel() {
            return imgpixel;
        }

        public void setImgpixel(String imgpixel) {
            this.imgpixel = imgpixel;
        }

        public String getImgsize() {
            return imgsize;
        }

        public void setImgsize(String imgsize) {
            this.imgsize = imgsize;
        }

        public String getInsafe() {
            return insafe;
        }

        public void setInsafe(String insafe) {
            this.insafe = insafe;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIninfo() {
            return ininfo;
        }

        public void setIninfo(String ininfo) {
            this.ininfo = ininfo;
        }

        public String getIndel() {
            return indel;
        }

        public void setIndel(String indel) {
            this.indel = indel;
        }

        public String getCdate() {
            return cdate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }

        public String getIdop() {
            return idop;
        }

        public void setIdop(String idop) {
            this.idop = idop;
        }

        public String getOpcreatetime() {
            return opcreatetime;
        }

        public void setOpcreatetime(String opcreatetime) {
            this.opcreatetime = opcreatetime;
        }

        public Object getOpuploadtime() {
            return opuploadtime;
        }

        public void setOpuploadtime(Object opuploadtime) {
            this.opuploadtime = opuploadtime;
        }

        public String getOpstate() {
            return opstate;
        }

        public void setOpstate(String opstate) {
            this.opstate = opstate;
        }

        public String getOptype() {
            return optype;
        }

        public void setOptype(String optype) {
            this.optype = optype;
        }
    }
}
