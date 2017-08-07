package com.qiaoxg.myjslogindemo.js;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.qiaoxg.myjslogindemo.utils.UIHelp;

/**
 * Created by admin on 2017/8/7.
 */

public class LoginJs {

    private static final String TAG = "LoginJs";

    private onLoginCallBack mCallBack;

    public LoginJs(onLoginCallBack callBack) {
        this.mCallBack = callBack;
    }

    @JavascriptInterface
    public void onLogin(String name, String password) {
        if (!name.equals("Qiaoxg") || !password.equals("123456")) {
            mCallBack.onLoginFail("用户名或者密码不正确");
        } else {
            mCallBack.onLoginSuccess();
        }
    }

    @JavascriptInterface
    public void showToastMessage(String msg) {
        UIHelp.showToast(msg);
    }

    public interface onLoginCallBack {
        void onLoginFail(String msg);
        void onLoginSuccess();
    }
}
