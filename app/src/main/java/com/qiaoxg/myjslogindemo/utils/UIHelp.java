package com.qiaoxg.myjslogindemo.utils;

import android.widget.Toast;

import com.qiaoxg.myjslogindemo.base.MyApplication;

/**
 * Created by admin on 2017/8/7.
 */

public class UIHelp {

    private static Toast mToast;

    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
