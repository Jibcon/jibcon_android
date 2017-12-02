package com.sm_arts.jibcon.ui.main.conshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sm_arts.jibcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-03-30.
 */

public class ConshopFragment extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    @BindView(R.id.webview) WebView mWebView;

    public ConshopFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainconshop_conshop_fragment, container, false);
        String url = "http://smarts.asuscomm.com/wordpress/";
        ButterKnife.bind(this, view);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient());

        return view;
    }
}
