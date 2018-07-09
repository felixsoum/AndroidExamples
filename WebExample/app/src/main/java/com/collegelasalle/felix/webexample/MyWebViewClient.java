package com.collegelasalle.felix.webexample;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("www.lasallecollege.com")) {
            return false;
        }
        return true;
    }

}
