package io.github.ovso.news.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public abstract class WebActivityModule {

  @Singleton @Provides public static WebPresenter provideMainPresenter(WebActivity act) {
    return new WebPresenterImpl(act);
  }
}
