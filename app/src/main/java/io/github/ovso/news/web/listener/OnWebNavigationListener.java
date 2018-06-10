package io.github.ovso.news.web.listener;

public interface OnWebNavigationListener {
  void onBack();
  void onForward();
  void onReload();
  void onShare();
}