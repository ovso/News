package io.github.ovso.news.web.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.baseview.BaseFragment;
import io.github.ovso.news.framework.listener.OnWebNavigationListener;
import io.github.ovso.news.framework.webview.OnWebChromeClientListener;
import io.github.ovso.news.framework.webview.OnWebViewClientListener;
import io.github.ovso.news.framework.webview.WebChromeClientImpl;
import io.github.ovso.news.framework.webview.WebViewClientImpl;
import timber.log.Timber;

public class WebFragment extends BaseFragment implements OnWebNavigationListener,
    OnWebChromeClientListener, OnWebViewClientListener {
  @BindView(R.id.webview) WebView webView;
  @BindView(R.id.progressbar) ContentLoadingProgressBar progressBar;
  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

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
    settings.setLoadWithOverviewMode(true);
    settings.setUseWideViewPort(true);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    settings.setDomStorageEnabled(true);
    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(true);
    webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    webView.setWebChromeClient(new WebChromeClientImpl.Builder().setOnWebChromeClientListener(this).build());
    webView.setWebViewClient(new WebViewClientImpl.Builder().setOnWebViewClientListener(this).build());
    webView.loadUrl(getArguments().getString("link"));
  }

  @Override public void onBack() {
    webView.goBack();
  }

  @Override public void onForward() {
    webView.goForward();
  }

  @Override public void onReload() {
    webView.reload();
  }

  @Override public void onShare() {
    Toast.makeText(getContext(), webView.getUrl(), Toast.LENGTH_SHORT).show();
  }

  @Override public void onProgressChanged(int progress) {
    Timber.d("progress = " + progress);
    if (progressBar != null) {
      progressBar.setProgress(progress);
    }
  }

  @Override public void onPageStarted() {
    Timber.d("onPageStarted");
    if (progressBar != null) {
      progressBar.show();
    }

  }

  @Override public void onPageFinished() {
    Timber.d("onPageFinished");
    if (progressBar != null) {
      progressBar.hide();
    }
  }
}