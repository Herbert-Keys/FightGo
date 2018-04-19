package com.example.herbert.fightgo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dalong.marqueeview.MarqueeView;
import com.example.herbert.fightgo.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarqueeActivity extends AppCompatActivity {

    @BindView(R.id.mMarqueeView)
    MarqueeView mMarqueeView;
    @BindView(R.id.webview)
    WebView webview;
    WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        ButterKnife.bind(this);
        mMarqueeView.setText("恭喜你获得了100W");
        mMarqueeView.setFocusable(true);
        mMarqueeView.requestFocus();
        mMarqueeView.startScroll(); //开始
        settings= webview.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://jsy.zhuineng.com/PDF_WORD/XTS.pdf");
    }
}
