package io.github.ovso.news.search;

import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.BasePresenter;
import io.github.ovso.news.framework.ObjectUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.search.model.Website;
import io.github.ovso.news.search.net.SearchNetwork;
import io.reactivex.disposables.CompositeDisposable;

public class SearchViewPresenterImpl extends BasePresenter<SearchViewPresenter.View>
    implements SearchViewPresenter {
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private SearchNetwork network;
  private BaseAdapterDataModel<Website> adapterDataModel;

  SearchViewPresenterImpl(View view, SchedulersFacade schedulers, SearchNetwork network,
      BaseAdapterDataModel<Website> adapterDataModel) {
    super(view);
    this.schedulers = schedulers;
    this.network = network;
    this.adapterDataModel = adapterDataModel;
  }

  @Override public void onCreate() {
    view.setListener();
    view.setupRecyclerView();
  }

  @DebugLog @Override public void onQueryTextChange(String newText) {
    if(ObjectUtils.isEmpty(newText)) {
      adapterDataModel.clear();
      view.refresh();
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

  @Override public void onStop() {
    compositeDisposable.clear();
  }
}