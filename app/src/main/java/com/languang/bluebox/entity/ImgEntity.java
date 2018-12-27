package com.languang.bluebox.entity;

public class ImgEntity {
    /**
     * idimg : 36
     * uuid : 599DAFBF-B7EA-B3D6-A047-7C1CAF589589
     * srctype : raw
     * smallname : small_1542180917_3453.jpg
     * smallpath : uploads/small/20181114/
     * srcname : IMG_1432_HEIC.png
     * srcpath : uploads/raw/20181106/
     * srcmd5 : 599dafbfb7eab3d6a0477c1caf589589
     * shottime : 2018-11-06 20:18:04
     * imgpixel : 4032,3024
     * imgsize : 996052
     * insafe : 0
     * createtime : 2018-11-14 15:35:17
     * updatetime : 2018-11-14 15:36:05
     * ininfo : 1
     * indel : 0
     * cdate : 2018-11-06
     * imgdesc : 测试结果表明
     * imgtitle : 老家
     * tagids : ["27", "28", "29"]
     * addid : 30
     * camera : No
     * gps : 39.9667573056,116.3000035278
     * vocuuid : 0B786CFB-E23E-D717-81F4-E8B58CA58866
     * cdfilepath : null
     * cdtime : 2018-11-14 15:36:05
     * idcdrom : null
     * cdsn : null
     * meta : 老家,生日,测试
     * tags : {"27":"老家","28":"生日","29":"测试"}
     * location : 老家
     * voice : {"idvc":"5","vocuuid":"0B786CFB-E23E-D717-81F4-E8B58CA58866","vcpath":"uploads/audio/20181114/","duration":"0","ctime":"2018-11-14 15:36:05","vcname":"20181114153605_3191.m4a"}
     */
//    private LinearLayout linearLayout;
//    private ImageView imageView;
//    private ImageView gou;
    private boolean checked = false;
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
    private String imgdesc;
    private String imgtitle;
    private String tagids;
    private String addid;
    private String camera;
    private String gps;
    private String vocuuid;
    private Object cdfilepath;
    private String cdtime;
    private Object idcdrom;
    private Object cdsn;
    private String meta;
    private String tags;
    private String location;
    private VoiceBean voice;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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

    public String getImgdesc() {
        return imgdesc;
    }

    public void setImgdesc(String imgdesc) {
        this.imgdesc = imgdesc;
    }

    public String getImgtitle() {
        return imgtitle;
    }

    public void setImgtitle(String imgtitle) {
        this.imgtitle = imgtitle;
    }

    public String getTagids() {
        return tagids;
    }

    public void setTagids(String tagids) {
        this.tagids = tagids;
    }

    public String getAddid() {
        return addid;
    }

    public void setAddid(String addid) {
        this.addid = addid;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getVocuuid() {
        return vocuuid;
    }

    public void setVocuuid(String vocuuid) {
        this.vocuuid = vocuuid;
    }

    public Object getCdfilepath() {
        return cdfilepath;
    }

    public void setCdfilepath(Object cdfilepath) {
        this.cdfilepath = cdfilepath;
    }

    public String getCdtime() {
        return cdtime;
    }

    public void setCdtime(String cdtime) {
        this.cdtime = cdtime;
    }

    public Object getIdcdrom() {
        return idcdrom;
    }

    public void setIdcdrom(Object idcdrom) {
        this.idcdrom = idcdrom;
    }

    public Object getCdsn() {
        return cdsn;
    }

    public void setCdsn(Object cdsn) {
        this.cdsn = cdsn;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

//    public TagsBean getTags() {
//        return tags;
//    }
//
//    public void setTags(TagsBean tags) {
//        this.tags = tags;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public VoiceBean getVoice() {
        return voice;
    }

    public void setVoice(VoiceBean voice) {
        this.voice = voice;
    }

//    public static class TagsBean {
//        List<String> map;
//
//        public void setMap(List<String> map) {
//            this.map = map;
//        }
//
//        public List<String> getMap() {
//            return map;
//        }
//    }

    public static class VoiceBean {
        /**
         * idvc : 5
         * vocuuid : 0B786CFB-E23E-D717-81F4-E8B58CA58866
         * vcpath : uploads/audio/20181114/
         * duration : 0
         * ctime : 2018-11-14 15:36:05
         * vcname : 20181114153605_3191.m4a
         */
        private String idvc;
        private String vocuuid;
        private String vcpath;
        private String duration;
        private String ctime;
        private String vcname;

        public String getIdvc() {
            return idvc;
        }

        public void setIdvc(String idvc) {
            this.idvc = idvc;
        }

        public String getVocuuid() {
            return vocuuid;
        }

        public void setVocuuid(String vocuuid) {
            this.vocuuid = vocuuid;
        }

        public String getVcpath() {
            return vcpath;
        }

        public void setVcpath(String vcpath) {
            this.vcpath = vcpath;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getVcname() {
            return vcname;
        }

        public void setVcname(String vcname) {
            this.vcname = vcname;
        }
    }
}
