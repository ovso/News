package io.github.ovso.news.framework.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.news.listup.ListUpActivity;
import io.github.ovso.news.listup.ListUpActivityModule;
import io.github.ovso.news.web.WebActivity;
import io.github.ovso.news.web.WebActivityModule;
import io.github.ovso.news.search.SearchActivity;
import io.github.ovso.news.search.SearchActivityModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton
  @ContributesAndroidInjector(modules = { WebActivityModule.class })
  abstract WebActivity bindMainActivity();

  @Singleton
  @ContributesAndroidInjector(modules = { ListUpActivityModule.class })
  abstract ListUpActivity bindListUpActivity();

  @Singleton
  @ContributesAndroidInjector(modules = { SearchActivityModule.class })
  abstract SearchActivity bindSearchActivity();
}