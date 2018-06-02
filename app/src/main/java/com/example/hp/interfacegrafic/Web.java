package com.example.hp.interfacegrafic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends AppCompatActivity
{
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String url = (String)extras.get("valor");

        webView = (WebView)findViewById(R.id.webGmail);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);
    }
}
