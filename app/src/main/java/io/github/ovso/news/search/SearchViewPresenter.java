package io.github.ovso.news.search;

import android.support.annotation.StringRes;
import io.github.ovso.news.search.model.Website;

public interface SearchViewPresenter {

  void onCreate();

  void onStop();

  void onItemClick(Website item);

  void onSearchTextChanged(String newText);

  interface View {

    void showErrorMessage(@StringRes int resId);

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void setupRecyclerView();

    void refresh();

    void allRefresh(); // why... --> recyclerview index bug...

    void finish();
  }
}
