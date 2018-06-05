package io.github.ovso.news.web.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.baseview.BaseFragment;
import io.github.ovso.news.framework.listener.OnWebNavigationListener;

public class WebFragment extends BaseFragment implements OnWebNavigationListener {
  @BindView(R.id.webview) WebView webView;

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
    webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient());
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
}