package io.github.ovso.news.web.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.baseview.BaseFragment;

public class WebFragment extends BaseFragment {
  @BindView(R.id.webview) WebView webView;

  public static WebFragment newInstance(Bundle args) {
    WebFragment f = new WebFragment();
    f.setArguments(args);
    return f;
  }

  @Override protected int getLayoutResID() {
    return R.layout.fragment_web;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(getArguments().getString("link"));
  }
}