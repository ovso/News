package io.github.ovso.news.web.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.baseview.BaseFragment;
import io.github.ovso.news.web.listener.OnWebNavigationListener;
import io.github.ovso.news.web.listener.OnWebViewStatusListener;

public class WebFragment extends BaseFragment
    implements OnWebNavigationListener {
  @BindView(R.id.webview) WebView webView;
  @BindView(R.id.root_view) ViewGroup rootView;

  private OnWebViewStatusListener onWebViewStatusListener;
  private int position;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    onWebViewStatusListener = (OnWebViewStatusListener) context;
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
    webView = getView().findViewById(R.id.webview);
    position = getArguments().getInt("position");
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
    webView.setWebChromeClient(new WebChromeClient() {
      @Override public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        onWebViewStatusListener.onProgress(newProgress, position);
      }
    });
    webView.setWebViewClient(new WebViewClient() {
      @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        onWebViewStatusListener.onPageStarted(position);
      }

      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        onWebViewStatusListener.onPageFinished(position);
      }
    });
    webView.loadUrl(getArguments().getString("link"));
  }

  @Override public boolean onBack() {
    if (webView.canGoBack()) {
      webView.goBack();
      return true;
    } else {
      return false;
    }
  }

  @Override public boolean onForward() {
    if (webView.canGoForward()) {
      webView.goForward();
      return true;
    } else {
      return false;
    }
  }

  @Override public void onReload() {
    webView.reload();
  }

  @Override public String getUrl() {
    return webView.getUrl();
  }

  @Override public void onDestroyView() {
    rootView.removeView(webView);
    webView.destroy();
    super.onDestroyView();
  }
}