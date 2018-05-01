package io.github.ovso.news.search;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.search.adapter.SearchAdapter;
import io.github.ovso.news.search.model.Website;

@Module public abstract class SearchActivityViewModule {

  @Binds abstract SearchViewPresenter.View bindSearchView(SearchActivity activity);

  @Binds abstract OnRecyclerItemClickListener<Website> bindOnRecyclerItemClickListener(
      SearchActivity activity);
}
