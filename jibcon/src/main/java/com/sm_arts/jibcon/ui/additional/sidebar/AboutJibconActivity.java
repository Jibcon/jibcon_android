package com.sm_arts.jibcon.ui.additional.sidebar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutJibconActivity extends BaseActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;

    @OnClick(R.id.imageview_sidebar_aboutjibcon) void imageview_sidebar_aboutjibcon() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_aboutjibconactivity_activity);
        ButterKnife.bind(this);

        mWebView = (WebView) findViewById(R.id.about_jibcon_webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true); // fit to webview size
        mWebSettings.setUseWideViewPort(true); // fit to webview size
        mWebSettings.setBuiltInZoomControls(true); // zoom
        mWebSettings.setSupportZoom(true); // zoom

        mWebView.loadUrl("https://www.sm-arts.io");
    }
}
