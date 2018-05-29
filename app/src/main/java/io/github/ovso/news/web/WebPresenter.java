package io.github.ovso.news.web;

import android.content.Context;
import android.content.Intent;
import io.github.ovso.news.db.WebsiteEntity;
import java.util.List;

public interface WebPresenter {

  void onDestroy();

  void onCreate(Intent intent);

  interface View {

    Context getContext();

    void setupViewPager(List<WebsiteEntity> items);

    void navigateToPosition(int position);
  }
}
