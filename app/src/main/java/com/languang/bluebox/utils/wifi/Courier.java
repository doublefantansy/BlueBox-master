package com.languang.bluebox.utils.wifi;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Vector;

/**
 * 消息中转
 *
 * @author mrj
 * @date 2017/10/9
 */
public class Courier {
    private static Handler mainHandler;
    private static Vector<Message> abandonMessage = new Vector<Message>();

    public static void setMainHander(Handler hander) {
        mainHandler = hander;
    }

    public static void send(Message message) {
        Log.d("marj", "send message :" + message.what);
        if (null != mainHandler) {
            mainHandler.sendMessage(message);
        } else {
            abandonMessage.add(message);
        }
    }

    /**
     * 发送消息
     *
     * @param what
     */
    public static void send(int what) {
        Message message = Message.obtain();
        message.what = what;
        send(message);
    }

    /**
     * 发送消息
     *
     * @param what
     */
    public static void send(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        send(message);
    }

    /**
     * 回收
     */
    public static void recycle() {
        mainHandler = null;
        abandonMessage.clear();
    }
}
