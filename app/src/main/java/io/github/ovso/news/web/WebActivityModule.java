package io.github.ovso.news.web;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import javax.inject.Singleton;

@Module public class WebActivityModule {

  @Singleton @Provides
  public WebPresenter provideMainPresenter(WebPresenter.View view, AppDatabase database) {
    return new WebPresenterImpl(view, database);
  }
}
