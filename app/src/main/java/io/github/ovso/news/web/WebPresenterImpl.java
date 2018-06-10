package io.github.ovso.news.web;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.web.apdater.WebAdapterDataModel;
import io.github.ovso.news.web.frag.WebFragment;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebPresenterImpl implements WebPresenter {

  private WebPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private int viewPagerPosition;
  private WebAdapterDataModel<Fragment> adapterDataModel;
  public WebPresenterImpl(WebPresenter.View view, AppDatabase database,
      SchedulersFacade schedulers, WebAdapterDataModel<Fragment> adapterDataModel) {
    this.view = view;
    this.database = database;
    this.schedulers = schedulers;
    this.adapterDataModel = adapterDataModel;
  }

  @Override public void onCreate(Intent intent) {
    view.setupViewPager();
    viewPagerPosition = intent.getIntExtra("position", 0);
    database.websiteDao().getLiveDataItems().observe((LifecycleOwner) view.getContext(),
        $items -> compositeDisposable.addAll(Observable.fromCallable(() -> {
          final List<WebsiteEntity> items = $items;
          Collections.sort(items,
              (o1, o2) -> o1.position < o2.position ? -1 : o1.position > o2.position ? 1 : 0);
          return items;
        }).subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(entities -> {
          adapterDataModel.addAll(generateFragments(entities));
          view.refresh();
          view.gotoPageOnViewPager(viewPagerPosition);
          handlingForPageMoveButton(entities.size());
        })));
  }

  private List<Fragment> generateFragments(final List<WebsiteEntity> $items) {
    List<Fragment> fragments = new ArrayList<>();
    for (int i = 0; i < $items.size(); i++) {
      Bundle args = new Bundle();
      args.putString("link", $items.get(i).getLink());
      args.putInt("position", i);
      fragments.add(WebFragment.newInstance(args));
    }
    return fragments;
  }
  /*
      List<Fragment> fragments = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      Bundle args = new Bundle();
      args.putString("link", items.get(i).getLink());
      args.putInt("position", i);
      fragments.add(WebFragment.newInstance(args));
    }

   */
  //사용하지 않음
  @Override
  public void onPageChange(int position, int itemCount, boolean canGoBack, boolean canGoForward) {
    viewPagerPosition = position;
    handlingForPageMoveButton(itemCount);
    handlingForWebBackButton(canGoBack);
    handlingForWebForwardButton(canGoForward);
  }

  @Override public void onPageChange(int position, int itemCount) {
    viewPagerPosition = position;
    handlingForPageMoveButton(itemCount);
    initProgress();
  }

  private void initProgress() {
    view.setWebProgress(0);
    view.hideProgressBar();
  }

  @Override public void onProgress(int progress, int fragmentPosition) {
    if (isSynchronizedPosition(fragmentPosition)) {
      view.setWebProgress(progress);
    }
  }

  @Override public void onPageStarted(int fragmentPosition) {
    if (isSynchronizedPosition(fragmentPosition)) {
      view.showProgressBar();
    }
  }

  @Override public void onPageFinished(int fragmentPosition) {
    if (isSynchronizedPosition(fragmentPosition)) {
      view.hideProgressBar();
    }
  }

  private boolean isSynchronizedPosition(int fragmentPosition) {
    return viewPagerPosition == fragmentPosition;
  }

  private void handlingForWebForwardButton(boolean canGoForward) {
    if (canGoForward) {
      view.enableForwardButton();
    } else {
      view.disableForwardButton();
    }
  }

  private void handlingForWebBackButton(boolean canGoBack) {
    if (canGoBack) {
      view.enableBackButton();
    } else {
      view.disableBackButton();
    }
  }

  private void handlingForPageMoveButton(int itemCount) {
    if (isFirstPosition(viewPagerPosition)) {
      view.hideLeftButton();
    } else if (isLastPosition(viewPagerPosition, itemCount)) {
      view.hideRightButton();
    } else {
      view.showLeftButton();
      view.showRightButton();
    }
  }

  @Override public void onNaviClick(int id) {
    switch (id) {
      case R.id.back_button:
        view.moveToBackOnWeb();
        break;
      case R.id.forward_button:
        view.moveToForwardOnWeb();
        break;
      case R.id.refresh_button:
        view.reloadOnWeb();
        break;
      case R.id.share_button:
        view.shareOnWeb();
        break;
      case R.id.listup_button:
        view.navigateToListUp();
        break;
    }
  }

  @Override public void canGoBack(boolean canGoBack, int fragmentPosition) {
    if (isSynchronizedPosition(fragmentPosition)) {
      if (canGoBack) {
        view.enableBackButton();
      } else {
        view.disableBackButton();
      }
    }
  }

  @Override public void canGoForward(boolean canGoForward, int fragmentPosition) {
    if (isSynchronizedPosition(fragmentPosition)) {
      if (canGoForward) {
        view.enableForwardButton();
      } else {
        view.disableForwardButton();
      }
    }
  }

  @Override public void onViewPagerLeftClick(int viewPagerCurrentPosition) {
    view.gotoPageOnViewPager(viewPagerCurrentPosition - 1);
  }

  @Override public void onViewPagerRightClick(int viewPagerCurrentPosition) {
    view.gotoPageOnViewPager(viewPagerCurrentPosition + 1);
  }

  private boolean isFirstPosition(int position) {
    return position == 0;
  }

  private boolean isLastPosition(int position, int itemCount) {
    return position == (itemCount - 1);
  }

  @Override public void onDestroy() {
    //database.close();
  }
}
