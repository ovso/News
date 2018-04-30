package io.github.ovso.news.web;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public abstract class WebActivityModule {

  @Singleton @Provides public static WebPresenter provideMainPresenter(WebActivity act) {
    return new WebPresenterImpl(act);
  }
}
