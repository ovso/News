package io.github.ovso.news.framework.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import lombok.Setter;
import lombok.experimental.Accessors;

public class WebChromeClientImpl extends WebChromeClient {
  private OnWebChromeClientListener onWebChromeClientListener;

  private WebChromeClientImpl(Builder builder) {
    onWebChromeClientListener = builder.onWebChromeClientListener;
  }

  @Override public void onProgressChanged(WebView view, int newProgress) {
    super.onProgressChanged(view, newProgress);
    if (onWebChromeClientListener != null) {
      onWebChromeClientListener.onProgressChanged(newProgress);
    }
  }

  public static class Builder {

    @Setter @Accessors(chain = true) private OnWebChromeClientListener onWebChromeClientListener;

    public WebChromeClientImpl build() {
      return new WebChromeClientImpl(this);
    }
  }
}
