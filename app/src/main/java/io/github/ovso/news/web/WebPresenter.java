package io.github.ovso.news.web;

import android.content.Context;
import io.github.ovso.news.db.WebsiteEntity;
import java.util.List;

public interface WebPresenter {

  void onCreate();

  void onDestroy();

  interface View {

    void setupViewPager();

    Context getContext();

    void setupViewPager(List<WebsiteEntity> items);
  }
}
