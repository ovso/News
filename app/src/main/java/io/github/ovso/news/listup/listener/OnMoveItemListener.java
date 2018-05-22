package io.github.ovso.news.listup.listener;

import io.github.ovso.news.db.WebsiteEntity;

public interface OnMoveItemListener {
  void onMoveItem(WebsiteEntity moveItem, int fromPosition, int toPosition);
}
