package io.github.ovso.news.listup.listener;

import java.util.List;

import io.github.ovso.news.db.WebsiteEntity;

public interface OnAdapterListener {
    void onItemDragFinished(List<WebsiteEntity> items);
}
