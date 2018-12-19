package com.languang.bluebox.basework.utils;

import android.content.Context;

import com.mrj.framworklib.view.CustomProgressDialog;

/**
 * loading管理
 *
 * @author 49829
 * @date 2017/11/28
 */

public class ProgressDialogUtil {

    private Context context;
    private CustomProgressDialog dialog;

    public ProgressDialogUtil(Context context, CustomProgressDialog dialog) {
        this.context = context;
        this.dialog = dialog;
    }


    public void showDialog(String content) {
        dialog = new CustomProgressDialog(context, CustomProgressDialog.THEME_DARK, content);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


}
