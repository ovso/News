package io.github.ovso.news.framework.listener;

import android.view.View;

public abstract class OnViewItemClickListener<T> implements View.OnClickListener {
  @Override public void onClick(View v) {
    onItemClick((T) v.getTag());
  }

  protected abstract void onItemClick(T item);
}
