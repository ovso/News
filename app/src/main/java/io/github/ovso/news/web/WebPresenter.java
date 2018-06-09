package io.github.ovso.news.web;

import android.content.Context;
import android.content.Intent;
import io.github.ovso.news.db.WebsiteEntity;
import java.util.List;

public interface WebPresenter {

  void onDestroy();

  void onCreate(Intent intent);

  void onNaviClick(int id);

  void onPageChange(int position, int itemCount, boolean canGoBack, boolean canGoForward);

  void onProgress(int progress, int positionOfFragment);

  void onPageStarted(int fragmentPosition);

  void onPageFinished(int fragmentPosition);

  void canGoBack(boolean canGoBack, int fragmentPosition);

  void canGoForward(boolean canGoForward, int fragmentPosition);

  void onViewPagerLeftClick(int viewPagerCurrentPosition);

  void onViewPagerRightClick(int viewPagerCurrentPosition);

  interface View {

    Context getContext();

    void setupViewPager(List<WebsiteEntity> items);

    void navigateToPosition(int position);

    void hideLeftButton();

    void hideRightButton();

    void showLeftButton();

    void showRightButton();

    void navigateToListUp();

    void moveToBackOnWeb();

    void moveToForwardOnWeb();

    void reloadOnWeb();

    void shareOnWeb();

    void enableBackButton();

    void disableBackButton();

    void enableForwardButton();

    void disableForwardButton();

    void gotoPageOnViewPager(int position);

    void setWebProgress(int progress);

    void showProgressBar();

    void hideProgressBar();
  }
}
