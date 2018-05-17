package io.github.ovso.news.listup.listener;

import io.github.ovso.news.db.WebsiteEntity;

public interface OnPerformDeleteActionListener {
  void onSwipeDelete(WebsiteEntity remove);
}
