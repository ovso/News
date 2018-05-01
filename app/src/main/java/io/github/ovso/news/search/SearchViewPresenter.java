package io.github.ovso.news.search;

import android.support.annotation.StringRes;

public interface SearchViewPresenter {

  void onCreate();

  boolean onQueryTextChange(String newText);

  void onStop();

  interface View {

    void setListener();

    void showErrorMessage(@StringRes int resId);
    void showMessage(@StringRes int resId);
    void showMessage(String msg);
  }
}
