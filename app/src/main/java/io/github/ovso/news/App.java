package io.github.ovso.news;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.github.ovso.news.framework.AppInitUtils;

public class App extends DaggerApplication {
  public static boolean DEBUG = false;
  private static App instance;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    DEBUG = AppInitUtils.debug(this);
    AppInitUtils.timber(DEBUG);
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
