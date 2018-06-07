package io.github.ovso.news.web.listener;

public interface OnWebViewStatusListener {
  void onProgress(int progress, int itemPosition);
  void onPageStarted(int itemPosition);
  void onPageFinished(int itemPosition);
  void canGoBack(boolean canGoBack);
  void canGoForward(boolean canGoForward);
}
