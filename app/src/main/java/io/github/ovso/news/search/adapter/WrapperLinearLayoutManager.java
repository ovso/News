package io.github.ovso.news.search.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import timber.log.Timber;

public class WrapperLinearLayoutManager extends LinearLayoutManager {
  public WrapperLinearLayoutManager(Context context) {
    super(context);
  }

  @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    try {
      super.onLayoutChildren(recycler, state);
    } catch (IndexOutOfBoundsException e) {
      Timber.d(e);
    }
  }
}
