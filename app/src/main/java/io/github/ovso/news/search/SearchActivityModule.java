package io.github.ovso.news.search;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public abstract class SearchActivityModule {

  @Singleton @Provides
  public static SearchViewPresenter provideSearchViewPresenter(SearchActivity act) {
    return new SearchViewPresenterImpl(act);
  }
}
