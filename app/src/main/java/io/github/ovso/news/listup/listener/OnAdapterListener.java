package io.github.ovso.news.listup.listener;

public interface OnAdapterListener<T> {
    void onItemDragFinished();

    void onItemClick(T item);

    boolean onItemLongClick(int position);
}
