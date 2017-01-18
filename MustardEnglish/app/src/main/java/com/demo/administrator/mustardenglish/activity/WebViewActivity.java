package com.demo.administrator.mustardenglish.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.demo.administrator.mustardenglish.R;

import org.w3c.dom.Text;

public class WebViewActivity extends AppCompatActivity {
    WebView wv;
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        tv_title=(TextView) findViewById(R.id.tv_title);
        wv = (WebView) findViewById(R.id.wv1);
        wv.loadUrl("http://blog.csdn.net/ghd2000/article/details/5844616");
        wv.setWebViewClient(new WebViewClient()
        {
            @Override
            // 在WebView中而不是默认浏览器中显示页面
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {
                tv_title.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                tv_title.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }
        });
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        // 在WebView中而不是默认浏览器中显示页面
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
           // tv_title.setVisibility(View.GONE);
            //开始
            super.onPageFinished(view, url);
        }

    }
}
