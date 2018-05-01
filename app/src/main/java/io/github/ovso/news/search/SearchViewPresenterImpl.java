package io.github.ovso.news.search;

import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.BasePresenter;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.search.model.WebsiteResult;
import io.github.ovso.news.search.net.SearchNetwork;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SearchViewPresenterImpl extends BasePresenter<SearchViewPresenter.View>
    implements SearchViewPresenter {
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private SearchNetwork network;

  SearchViewPresenterImpl(View view, SchedulersFacade schedulers, SearchNetwork network) {
    super(view);
    this.schedulers = schedulers;
    this.network = network;
  }

  @Override public void onCreate() {
    view.setListener();
  }

  @DebugLog @Override public boolean onQueryTextChange(String newText) {
    compositeDisposable.clear();
    compositeDisposable.add(network.getWebsiteResult(newText)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui()).subscribe(new Consumer<WebsiteResult>() {
          @DebugLog @Override public void accept(WebsiteResult result) throws Exception {
            
          }
        }, throwable -> view.showErrorMessage(R.string.error)));
    return false;
  }

  @Override public void onStop() {
    compositeDisposable.clear();
  }
}