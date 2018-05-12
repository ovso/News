package io.github.ovso.news.web;

public interface WebPresenter {

    void onCreate();

  void onDestroy();

  interface View {

      void setupViewPager();
    }
}
