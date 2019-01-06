package com.languang.bluebox.activity.property;

import java.util.List;

class WaiseBean {
    /**
     * name : usb5000
     * total : 42139451392
     * free : 29650931712
     * info : 12488519680
     */
    String status;
    List<Usb> usb;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Usb> getUsb() {
        return usb;
    }

    public void setUsb(List<Usb> usb) {
        this.usb = usb;
    }

    class Usb {
        private String name;
        private long total;
        private long free;
        private long info;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getFree() {
            return free;
        }

        public void setFree(long free) {
            this.free = free;
        }

        public long getInfo() {
            return info;
        }

        public void setInfo(long info) {
            this.info = info;
        }
    }
}
