package io.github.ovso.news.framework.listener;

import android.support.v7.widget.SearchView;

public abstract class OnSimpleQueryTextListener implements SearchView.OnQueryTextListener {
  @Override public boolean onQueryTextSubmit(String query) {
    return false;
  }
}