package io.github.ovso.news.search;

import java.util.List;

import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.baseview.BasePresenter;
import io.github.ovso.news.framework.utils.ObjectUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.search.model.Website;
import io.github.ovso.news.search.net.SearchNetwork;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class SearchViewPresenterImpl extends BasePresenter<SearchViewPresenter.View>
    implements SearchViewPresenter {
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private SearchNetwork network;
  private BaseAdapterDataModel<Website> adapterDataModel;
  private AppDatabase database;

  SearchViewPresenterImpl(View view, SchedulersFacade schedulers, SearchNetwork network,
      BaseAdapterDataModel<Website> adapterDataModel, AppDatabase database) {
    super(view);
    this.schedulers = schedulers;
    this.network = network;
    this.adapterDataModel = adapterDataModel;
    this.database = database;
  }

  @Override
  public void onCreate() {
    view.setupRecyclerView();
  }

  @Override
  public void onStop() {
    compositeDisposable.clear();
  }

  @DebugLog
  @Override
  public void onItemClick(Website item) {
    compositeDisposable.add(Observable.fromCallable(() -> {
      WebsiteEntity entity = WebsiteEntity.convertWebsiteToEntiry(item);
      List<WebsiteEntity> dbItems = database.websiteDao().getItems();
      entity.setPosition(dbItems.size());
      database.websiteDao().insert(entity);
      return entity;
    })
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(result -> view.finish(), new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) throws Exception {
            Timber.d(throwable);
            view.showErrorMessage(R.string
                .error);
          }
        }));
  }

  @Override public void onSearchTextChanged(String newText) {
    if (ObjectUtils.isEmpty(newText)) {
      adapterDataModel.clear();
      view.allRefresh(); // recyclerview index bug...( create wrapper class)
      return;
    }
    adapterDataModel.clear();
    view.refresh();
    compositeDisposable.clear();
    compositeDisposable.add(network.getWebsiteResult(newText)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui()).subscribe(result -> {
          adapterDataModel.addAll(result.getItems());
          view.refresh();
        }, throwable -> view.showErrorMessage(R.string.error)));
  }
}