package com.example.herbert.fightgo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.dalong.marqueeview.MarqueeView;
import com.example.herbert.fightgo.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarqueeActivity extends AppCompatActivity {

    @BindView(R.id.mMarqueeView)
    MarqueeView mMarqueeView;
    @BindView(R.id.webview)
    WebView webview;
    WebSettings settings;
    List<String> list = new ArrayList();
    int i = 0;
    @BindView(R.id.mv_main3)
    com.marquee.dingrui.marqueeviewlib.MarqueeView mV3;
    WindowManager windowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        ButterKnife.bind(this);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        windowManager=(WindowManager)this.getSystemService(Context.WINDOW_SERVICE);;
//        mMarqueeView.setText(list.get(0));
//        mMarqueeView.setOnMargueeListener(new MarqueeView.OnMargueeListener() {
//            @Override
//            public void onRollOver() {
//                next(++i);
//            }
//        });
//        mMarqueeView.setFocusable(true);
//        mMarqueeView.requestFocus();
//        mMarqueeView.startScroll(); //开始
//        settings= webview.getSettings();
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setJavaScriptEnabled(true);
//
//        webview.setWebViewClient(new WebViewClient());
//        webview.loadUrl("http://jsy.zhuineng.com/PDF_WORD/XTS.pdf");
        mV3.setContent(list);
        mV3.setTextDistance(200);//设置间距
        mV3.setTextSpeed(3);//设置速度
        mV3.setTextColor(R.color.colorAccent);//设置颜色
        mV3.setTextSize(17);//设置文字大小
        mV3.stopRoll();//停止滚动
        mV3.continueRoll();//继续滚动
    }

    private void next(int i) {
        if (i < list.size()) {
            mMarqueeView.setText(list.get(i));
            mMarqueeView.setFocusable(true);
            mMarqueeView.requestFocus();
            mMarqueeView.startScroll(); //开始
        } else {
            mMarqueeView.setVisibility(View.GONE);
        }
    }
}
