package io.github.ovso.news.listup.listener;

import java.util.List;

public interface OnAdapterListener<T> {
    void onItemDragFinished(List<T> items);
    void onItemClick(T item);
    void onItemLongClick(T item);
}
