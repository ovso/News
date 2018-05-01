package io.github.ovso.news.search;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.search.adapter.SearchAdapter;
import io.github.ovso.news.search.model.Website;
import io.github.ovso.news.search.net.SearchNetwork;
import javax.inject.Singleton;

@Module public class SearchActivityModule {

  @Singleton @Provides
  public SearchViewPresenter provideSearchViewPresenter(SearchViewPresenter.View view,
      SchedulersFacade schedulers, SearchNetwork network,
      BaseAdapterDataModel<Website> adapterDataModel) {
    return new SearchViewPresenterImpl(view, schedulers, network, adapterDataModel);
  }

  @DebugLog @Singleton @Provides
  public SearchAdapter provideSearchAdapter(OnRecyclerItemClickListener<Website> listener) {
    return new SearchAdapter.Builder().setOnItemClickListener(listener).build();
  }

  @DebugLog @Provides public BaseAdapterView provideAdapterView(SearchAdapter adapter) {
    return adapter;
  }

  @DebugLog @Provides
  public BaseAdapterDataModel<Website> provideAdapterDataModel(SearchAdapter adapter) {
    return adapter;
  }
}