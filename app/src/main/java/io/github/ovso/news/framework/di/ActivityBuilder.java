package io.github.ovso.news.framework.di;

import io.github.ovso.news.web.WebActivityViewModule;
import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.news.listup.ListUpActivity;
import io.github.ovso.news.listup.ListUpActivityModule;
import io.github.ovso.news.listup.ListUpActivityViewModule;
import io.github.ovso.news.search.SearchActivity;
import io.github.ovso.news.search.SearchActivityModule;
import io.github.ovso.news.search.SearchActivityViewModule;
import io.github.ovso.news.web.WebActivity;
import io.github.ovso.news.web.WebActivityModule;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton
  @ContributesAndroidInjector(modules = { WebActivityModule.class, WebActivityViewModule.class })
  abstract WebActivity bindMainActivity();

  @Singleton
  @ContributesAndroidInjector(modules = {
      ListUpActivityModule.class, ListUpActivityViewModule
      .class
  })
  abstract ListUpActivity bindListUpActivity();

  @Singleton
  @ContributesAndroidInjector(modules = {
      SearchActivityModule.class, SearchActivityViewModule.class
  })
  abstract SearchActivity bindSearchActivity();
}