package io.github.ovso.news.web;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.news.R;
import io.github.ovso.news.data.Preferences;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.web.apdater.WebAdapterDataModel;
import io.github.ovso.news.web.frag.WebFragment;
import io.github.ovso.news.web.listener.OnWebNavigationListener;
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
  private OnWebNavigationListener onWebNavigationListener;

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
          hideOrShowPageNavigationButton();
          setupWebNavigationListener();
        })));
  }

  private void setupWebNavigationListener() {
    if (isFirstPosition()) {
      onWebNavigationListener =
          (OnWebNavigationListener) adapterDataModel.getItem(viewPagerPosition);
    }
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

  @Override public void onPageChange(int position) {
    viewPagerPosition = position;
    onWebNavigationListener = (OnWebNavigationListener) adapterDataModel.getItem(position);
    hideOrShowPageNavigationButton();
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

  private void hideOrShowPageNavigationButton() {
    if (isFirstPosition()) {
      view.hideLeftButton();
    } else if (isLastPosition()) {
      view.hideRightButton();
    } else {
      view.showLeftButton();
      view.showRightButton();
    }
  }

  @Override public void onNaviClick(int id) {
    switch (id) {
      case R.id.back_button:
        if (!onWebNavigationListener.onBack()) {
          view.showMessage(R.string.no_more_page_to_move);
        }
        break;
      case R.id.forward_button:
        if (!onWebNavigationListener.onForward()) {
          view.showMessage(R.string.no_more_page_to_move);
        }
        break;
      case R.id.refresh_button:
        onWebNavigationListener.onReload();
        break;
      case R.id.share_button:
        view.doShare(onWebNavigationListener.getUrl());
        break;
      case R.id.listup_button:
        view.navigateToListUp();
        break;
    }
  }

  @Override public void onViewPagerLeftClick() {
    view.gotoPageOnViewPager(viewPagerPosition - 1);
  }

  @Override public void onViewPagerRightClick() {
    view.gotoPageOnViewPager(viewPagerPosition + 1);
  }

  private boolean isFirstPosition() {
    return viewPagerPosition == 0;
  }

  private boolean isLastPosition() {
    return viewPagerPosition == (adapterDataModel.getSize() - 1);
  }

  @Override public void onDestroy() {
    //database.close();
    Prefs.putString(Preferences.KEY_LAST_SCREEN.get(), Preferences.SCREEN_WEB.get());
    Prefs.putInt(Preferences.KEY_WEB_POSITION.get(), viewPagerPosition);
  }
}
