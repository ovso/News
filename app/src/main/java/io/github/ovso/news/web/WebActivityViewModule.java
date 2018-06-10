package io.github.ovso.news.web;

import dagger.Binds;
import dagger.Module;

@Module public abstract class WebActivityViewModule {
  @Binds abstract WebPresenter.View bindWebView(WebActivity activity);
}
