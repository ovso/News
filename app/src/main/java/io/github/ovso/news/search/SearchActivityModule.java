package io.github.ovso.news.search;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
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
      BaseAdapterDataModel<Website> adapterDataModel, AppDatabase database) {
    return new SearchViewPresenterImpl(view, schedulers, network, adapterDataModel, database);
  }

  @Singleton @Provides
  public SearchAdapter provideSearchAdapter(OnRecyclerItemClickListener<Website> listener) {
    return new SearchAdapter.Builder().setOnItemClickListener(listener).build();
  }

  @Provides public BaseAdapterView provideAdapterView(SearchAdapter adapter) {
    return adapter;
  }

  @Provides
  public BaseAdapterDataModel<Website> provideAdapterDataModel(SearchAdapter adapter) {
    return adapter;
  }

  @Provides public AppDatabase provideAppDatabase(Context context) {
    return AppDatabase.getInstance(context);
  }
}