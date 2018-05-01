package io.github.ovso.news.search;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.search.model.Website;

@Module public abstract class SearchActivityViewModule {

  @Binds public abstract OnRecyclerItemClickListener<Website> provideOnRecyclerItemClickListener(
      SearchActivity activity);

  @Binds abstract SearchViewPresenter.View bindSearchView(SearchActivity activity);
}
