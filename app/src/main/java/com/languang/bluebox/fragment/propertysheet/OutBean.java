package com.languang.bluebox.fragment.propertysheet;

import java.util.Map;

public class OutBean {
    String status;
    Map<String, Nocd> files;

//    public class File {


        public class Nocd {
            int count;
            int imgcount;
            String movcount;
            long sumsize;
            Map<String, ImgEntitysp> files;

            public class ImgEntitysp {
                /**
                 * idimg : 71
                 * uuid : F0E68AA2-B8C9-357F-6214-154A699C002C
                 * srctype : raw
                 * smallname : small_1545793283_6746.jpg
                 * smallpath : uploads/small/20181226/
                 * srcname : 20181112150952.jpg
                 * srcpath : uploads/raw/20181226/
                 * srcmd5 : f0e68aa2b8c9357f6214154a699c002c
                 * shottime : 2018-12-26 11:01:23
                 * imgpixel : 1920,1080
                 * imgsize : 1074843
                 * insafe : 0
                 * createtime : 2018-12-26 11:01:23
                 * updatetime : 2018-12-27 09:00:35
                 * ininfo : 1
                 * indel : 0
                 * cdate : 2018-12-26
                 * imgdesc : 11
                 * imgtitle : 11
                 * tagids : ["36"]
                 * addid : 37
                 * camera : 11
                 * gps : 0
                 * vocuuid : 8A11640E-8BC6-B598-186C-744535BBC071
                 * cdfilepath : null
                 * cdtime : 2018-12-27 09:00:10
                 * idcdrom : null
                 * cdsn : null
                 * idop : 112
                 * opcreatetime : 2018-12-27 20:20:58
                 * opuploadtime : null
                 * opstate : 0
                 * optype : 3
                 */
                private int click;
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
                private String cdfilepath;
                private String cdtime;
                private String idcdrom;
                private String cdsn;
                private String idop;
                private String opcreatetime;
                private String opuploadtime;
                private String opstate;
                private String optype;

                public int getClick() {
                    return click;
                }

                public void setClick(int click) {
                    this.click = click;
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

                public String getCdfilepath() {
                    return cdfilepath;
                }

                public void setCdfilepath(String cdfilepath) {
                    this.cdfilepath = cdfilepath;
                }

                public String getCdtime() {
                    return cdtime;
                }

                public void setCdtime(String cdtime) {
                    this.cdtime = cdtime;
                }

                public String getIdcdrom() {
                    return idcdrom;
                }

                public void setIdcdrom(String idcdrom) {
                    this.idcdrom = idcdrom;
                }

                public String getCdsn() {
                    return cdsn;
                }

                public void setCdsn(String cdsn) {
                    this.cdsn = cdsn;
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

                public String getOpuploadtime() {
                    return opuploadtime;
                }

                public void setOpuploadtime(String opuploadtime) {
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
//        }
    }
}
