package com.aftercup.calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myWebView = new WebView(this);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);

        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databasePath);

        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("file:///android_asset/www/index.html");

        setContentView(myWebView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            myWebView.loadUrl("javascript:handleBackButton()");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }
        public void closeApp() {
            finish();
        }
    }
}