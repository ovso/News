package io.github.ovso.news;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.github.ovso.news.framework.AppInitUtils;
import io.github.ovso.news.framework.di.DaggerAppComponent;
import lombok.Getter;

public class App extends DaggerApplication {
  @Getter private static boolean DEBUG = false;
  @Getter private static App instance;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    DEBUG = AppInitUtils.isDebug(this);
    AppInitUtils.timber(DEBUG);
    AppInitUtils.stetho(this);
    AppInitUtils.prefs(this);
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}