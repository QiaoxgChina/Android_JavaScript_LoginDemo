package com.qiaoxg.myjslogindemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qiaoxg.myjslogindemo.R;
import com.qiaoxg.myjslogindemo.js.LoginJs;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private WebView mWebView;
    private View showAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWebView();
        showAlert = findViewById(R.id.showHtmlAlert);
        showAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorMsgOnHtml("");
            }
        });
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.webView_view);

        String loginFile = "file:///android_asset/login.html";
        mWebView.loadUrl(loginFile);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new LoginJs(new LoginJs.onLoginCallBack() {
            @Override
            public void onLoginFail(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showErrorMsgOnHtml(msg);
                    }
                });
            }

            @Override
            public void onLoginSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("file:///android_asset/main.html");
                    }
                });
            }
        }), "loginJs");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.e(TAG, "shouldOverrideUrlLoading: 重新加载网页");
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted: 开始加载网页");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "onPageFinished: 网页加载完毕");
                super.onPageFinished(view, url);
            }
        });
    }

    private void showErrorMsgOnHtml(String msg) {
        //注意此处要将双引号转为单引号
        String call = "javascript:showErrorMsg(\"" + msg + "\")";
        mWebView.loadUrl(call);
    }
}
