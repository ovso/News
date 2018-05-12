package io.github.ovso.news.web;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import javax.inject.Singleton;

@Module public abstract class WebActivityViewModule {
  @Binds abstract WebPresenter.View bindWebView(WebActivity activity);
}
