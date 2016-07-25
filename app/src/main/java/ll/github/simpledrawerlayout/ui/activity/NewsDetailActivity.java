package ll.github.simpledrawerlayout.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.NewsDetail;

/**
 * Created by liuwanyouyue on 2016/7/15.
 */
public class NewsDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private NewsDetail detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        detail = getIntent().getParcelableExtra("detail");

        initView();
//        init();
        startWebView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wv);
    }

    private void init() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);//使webview支持变焦
        //使webview自适应屏幕大小
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.requestFocus();

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl(detail.getArticle_url());
    }

    ProgressDialog progressDialog;
    public void startWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(detail.getArticle_url());

        mWebView.getSettings().setSupportZoom(true);//使webview支持变焦
        //使webview自适应屏幕大小
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.requestFocus();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(NewsDetailActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(progressDialog.isShowing() || null != progressDialog){
                    progressDialog.dismiss();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
