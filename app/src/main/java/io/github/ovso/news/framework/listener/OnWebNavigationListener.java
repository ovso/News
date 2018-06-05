package io.github.ovso.news.framework.listener;

public interface OnWebNavigationListener {
  void onBack();
  void onForward();
  void onReload();
  void onShare();
}