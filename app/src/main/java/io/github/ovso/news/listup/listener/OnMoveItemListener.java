package io.github.ovso.news.listup.listener;

import java.util.List;

import io.github.ovso.news.db.WebsiteEntity;

public interface OnMoveItemListener {
    void onMoveItem(List<WebsiteEntity> items);
}
