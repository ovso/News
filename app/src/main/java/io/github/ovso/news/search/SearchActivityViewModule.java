package io.github.ovso.news.search;

import dagger.Binds;
import dagger.Module;

@Module public abstract class SearchActivityViewModule {

  @Binds abstract SearchViewPresenter.View provideSearchView(SearchActivity activity);
}
