package com.languang.bluebox.entity;

import java.util.List;

public class SpeRes<T> {
    /**
     * ret : 200
     * data : {"status":"9999","ip":"125.85.184.200","version":"1970-01-01 08:00:00"}
     * msg :
     * debug : {"stack":["[#0 - 0ms - PHALAPI_INIT]/web/www/cloud/webapi/public/index.php(6)","[#1 - 1.3ms - PHALAPI_RESPONSE]/web/www/cloud/webapi/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 1.7ms - PHALAPI_FINISH]/web/www/cloud/webapi/vendor/phalapi/kernal/src/PhalApi.php(74)"],"sqls":[],"version":"2.2.3"}
     */
    private int ret;
    private T data;
    private String msg;
    private DebugBean debug;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DebugBean getDebug() {
        return debug;
    }

    public void setDebug(DebugBean debug) {
        this.debug = debug;
    }

    public static class DataBean {
        /**
         * status : 9999
         * ip : 125.85.184.200
         * version : 1970-01-01 08:00:00
         */
        private String status;
        private String ip;
        private String version;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class DebugBean {
        /**
         * stack : ["[#0 - 0ms - PHALAPI_INIT]/web/www/cloud/webapi/public/index.php(6)","[#1 - 1.3ms - PHALAPI_RESPONSE]/web/www/cloud/webapi/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 1.7ms - PHALAPI_FINISH]/web/www/cloud/webapi/vendor/phalapi/kernal/src/PhalApi.php(74)"]
         * sqls : []
         * version : 2.2.3
         */
        private String version;
        private List<String> stack;
        private List<?> sqls;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<String> getStack() {
            return stack;
        }

        public void setStack(List<String> stack) {
            this.stack = stack;
        }

        public List<?> getSqls() {
            return sqls;
        }

        public void setSqls(List<?> sqls) {
            this.sqls = sqls;
        }
    }
}
