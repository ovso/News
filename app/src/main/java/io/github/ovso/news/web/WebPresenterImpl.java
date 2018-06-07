package io.github.ovso.news.web;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Collections;
import java.util.List;

public class WebPresenterImpl implements WebPresenter {

  private WebPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;

  public WebPresenterImpl(WebPresenter.View view, AppDatabase database,
      SchedulersFacade schedulers) {
    this.view = view;
    this.database = database;
    this.schedulers = schedulers;
  }

  @Override public void onCreate(Intent intent) {
    database.websiteDao().getLiveDataItems().observe((LifecycleOwner) view.getContext(),
        $items -> compositeDisposable.addAll(Observable.fromCallable(() -> {
          final List<WebsiteEntity> items = $items;
          Collections.sort(items,
              (o1, o2) -> o1.position < o2.position ? -1 : o1.position > o2.position ? 1 : 0);
          return items;
        }).subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(entities -> {
          view.setupViewPager(entities);
          view.navigateToPosition(intent.getIntExtra("position", 0));
        })));
  }

  @Override public void onPageChange(int position, int itemCount) {
    if (isFirstPosition(position)) {
      view.hideLeftButton();
    } else if (isLastPosition(position, itemCount)) {
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

  @Override public void canGoBack(boolean canGoBack) {
    if(canGoBack) {
      view.enableBackButton();
    } else {
      view.disableBackButton();
    }
  }

  @Override public void canGoForward(boolean canGoForward) {
    if(canGoForward) {
      view.enableForwardButton();
    } else {
      view.disableForwardButton();
    }
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
