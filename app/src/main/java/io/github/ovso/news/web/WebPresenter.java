package io.github.ovso.news.web;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import io.github.ovso.news.db.WebsiteEntity;
import java.util.List;

public interface WebPresenter {

  void onDestroy();

  void onCreate(Intent intent);

  void onNaviClick(int id);

  void onPageChange(int position);

  void onProgress(int progress, int positionOfFragment);

  void onPageStarted(int fragmentPosition);

  void onPageFinished(int fragmentPosition);

  void onViewPagerLeftClick();

  void onViewPagerRightClick();

  interface View {

    Context getContext();

    void setupViewPager();

    void hideLeftButton();

    void hideRightButton();

    void showLeftButton();

    void showRightButton();

    void navigateToListUp();

    void gotoPageOnViewPager(int position);

    void setWebProgress(int progress);

    void showProgressBar();

    void hideProgressBar();

    void refresh();

    void showMessage(@StringRes int resId);

    void doShare(String url);

    void openBrowser(String url);
  }
}
