package io.github.ovso.news.listup;

import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class ListUpPresenterImpl implements ListUpPresenter {

  private SchedulersFacade schedulers;
  private ListUpPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<WebsiteEntity> adapterDataModel;

  public ListUpPresenterImpl(ListUpPresenter.View view, AppDatabase database, SchedulersFacade
      schedulers, BaseAdapterDataModel<WebsiteEntity> adapterDataModel) {
    this.view = view;
    this.database = database;
    this.schedulers = schedulers;
    this.adapterDataModel = adapterDataModel;
  }

  @Override
  public void onCreate() {
    view.setupRecyclerView();

    compositeDisposable.add(Observable.fromCallable(() -> database.websiteDao().getAll())
        .subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(items -> {
          Timber.d("items = " + items);
          adapterDataModel.addAll(items);
          view.refresh();
        }, throwable -> view.showErrorMessage(R.string.error)));
  }

  @Override
  public void onDestroy() {
    compositeDisposable.clear();
  }
}
