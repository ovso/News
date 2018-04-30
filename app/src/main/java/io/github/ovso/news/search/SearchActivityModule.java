package io.github.ovso.news.search;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.search.net.SearchNetwork;
import javax.inject.Singleton;

@Module public class SearchActivityModule {

  @Singleton @Provides
  public SearchViewPresenter provideSearchViewPresenter(SearchViewPresenter.View view,
      SchedulersFacade schedulers, SearchNetwork network) {
    return new SearchViewPresenterImpl(view, schedulers, network);
  }
}
