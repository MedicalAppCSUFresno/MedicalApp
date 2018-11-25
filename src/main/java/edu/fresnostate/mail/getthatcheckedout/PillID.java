package edu.fresnostate.mail.getthatcheckedout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class PillID extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_id);

        webview =(WebView)findViewById(R.id.PillId);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('header')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('topbanner-container')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('input-block pid-example text-center')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('ddc-breadcrumb ddc-breadcrumb-3')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox sideBoxM1ad')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox sideBoxPillApp')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox sideBoxMiddleAd sideBoxFloatRight')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox sideBoxSubscribe')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('footer')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('sideBox sideBoxMiddleAd sideBoxFloatRight')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('boxList clearAfter')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('display-ad display-ad-injection display-ad-dfp display-ad-300 display-ad-300-250')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('contentAd contentAdM1 contentAdAlone')[0].style.display='none'; " + "})()");
                webview.loadUrl("javascript:(function() { " + "var head = document.getElementsByClassName('footer-feature')[0].style.display='none'; " + "})()");

                }
            }
        );
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://www.drugs.com/imprints.php");
    }
}
