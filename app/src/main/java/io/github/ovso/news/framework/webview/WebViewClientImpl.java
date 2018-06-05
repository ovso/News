package io.github.ovso.news.framework.webview;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import lombok.Setter;
import lombok.experimental.Accessors;

public class WebViewClientImpl extends WebViewClient {
  @Setter private OnWebViewClientListener onWebViewClientListener;

  private WebViewClientImpl(Builder builder) {
    onWebViewClientListener = builder.onWebViewClientListener;
  }

  @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
    if (onWebViewClientListener != null) {
      onWebViewClientListener.onPageStarted();
    }
  }

  @Override public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    if (onWebViewClientListener != null) {
      onWebViewClientListener.onPageFinished();
    }
  }

  public static class Builder {
    @Setter @Accessors(chain = true) private OnWebViewClientListener onWebViewClientListener;

    public WebViewClientImpl build() {
      return new WebViewClientImpl(this);
    }
  }
}
