package io.github.ovso.news.framework.listener;

import android.support.v7.widget.SearchView;
import io.github.ovso.news.framework.ObjectUtils;

public abstract class OnSimpleQueryTextListener implements SearchView.OnQueryTextListener {
  @Override public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override public boolean onQueryTextChange(String newText) {
    if (!ObjectUtils.isEmpty(newText)) {
      onQueryTextChanged(newText);
    }
    return false;
  }

  protected abstract void onQueryTextChanged(String newText);
}