package com.languang.bluebox.fragment.facility;

import com.languang.bluebox.entity.SpeRes;

public class ResponseMessage1<T> {
    private int ret;
    private String msg;
    private T data;
    private SpeRes.DebugBean debug;

    public SpeRes.DebugBean getDebug() {
        return debug;
    }

    public void setDebug(SpeRes.DebugBean debug) {
        this.debug = debug;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
