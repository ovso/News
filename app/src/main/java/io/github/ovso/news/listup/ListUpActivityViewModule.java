package io.github.ovso.news.listup;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.listup.listener.OnAdapterListener;

@Module
public abstract class ListUpActivityViewModule {

  @Binds abstract OnAdapterListener<WebsiteEntity> provideOnAdapterListener(ListUpActivity activity);

  @Binds
  abstract ListUpPresenter.View bindListUpView(ListUpActivity activity);
}
