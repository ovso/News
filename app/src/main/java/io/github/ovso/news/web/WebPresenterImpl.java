package io.github.ovso.news.web;

import io.github.ovso.news.db.AppDatabase;

public class WebPresenterImpl implements WebPresenter {

  private WebPresenter.View view;
  private AppDatabase database;

  public WebPresenterImpl(WebPresenter.View view, AppDatabase database) {
    this.view = view;
    this.database = database;
  }

  @Override public void onCreate() {
    view.setupViewPager();
  }

  @Override public void onDestroy() {
    database.close();
  }
}
