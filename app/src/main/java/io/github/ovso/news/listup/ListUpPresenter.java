package io.github.ovso.news.listup;

import android.content.Context;
import android.support.annotation.StringRes;
import io.github.ovso.news.db.WebsiteEntity;

public interface ListUpPresenter {

  void onCreate();

  void onDestroy();

  void onSwipeDelete(WebsiteEntity remove);

  interface View {
    void showErrorMessage(@StringRes int resId);

    void setupRecyclerView();

    void refresh();

    Context getContext();
  }
}
