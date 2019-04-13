package com.languang.bluebox.fragment.facility;

import java.util.List;

public class HeziBean {
    String status;
    List<Every> dev;
    CountFile countfile;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Every> getDev() {
        return dev;
    }

    public void setDev(List<Every> dev) {
        this.dev = dev;
    }

    public CountFile getCountfile() {
        return countfile;
    }

    public void setCountfile(CountFile countfile) {
        this.countfile = countfile;
    }

    public class CountFile {
        String mov;
        String raw;

        public String getMov() {
            return mov;
        }

        public void setMov(String mov) {
            this.mov = mov;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }
    }

    public class Every {
        String name;
        long total;
        long free;
        long info;

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
