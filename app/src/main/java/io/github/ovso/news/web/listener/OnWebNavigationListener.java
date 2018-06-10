package io.github.ovso.news.web.listener;

public interface OnWebNavigationListener {
  boolean onBack();

  boolean onForward();

  void onReload();

  void onShare();
}