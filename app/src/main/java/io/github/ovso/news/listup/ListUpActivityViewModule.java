package io.github.ovso.news.listup;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;

@Module
public abstract class ListUpActivityViewModule {

  @Binds
  abstract OnRecyclerItemClickListener<WebsiteEntity> provideOnRecyclerItemClickListener(
      ListUpActivity activity);

  @Binds
  abstract ListUpPresenter.View bindListUpView(ListUpActivity activity);
}
