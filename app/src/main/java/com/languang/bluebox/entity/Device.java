package com.languang.bluebox.entity;

import java.util.List;

public class Device {
        /**
         * status : 9999
         * cdrom : []
         */
        private String status;
        private List<?> cdrom;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<?> getCdrom() {
            return cdrom;
        }

        public void setCdrom(List<?> cdrom) {
            this.cdrom = cdrom;
        }

}
