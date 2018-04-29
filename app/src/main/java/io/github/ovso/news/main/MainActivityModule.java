package io.github.ovso.news.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public abstract class MainActivityModule {

  @Singleton @Provides public static MainPresenter provideMainPresenter(MainActivity act) {
    return new MainPresenterImpl(act);
  }
}
