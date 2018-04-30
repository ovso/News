package io.github.ovso.news.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.news.listup.ListUpActivity;
import io.github.ovso.news.listup.ListUpActivityModule;
import io.github.ovso.news.main.MainActivity;
import io.github.ovso.news.main.MainActivityModule;
import io.github.ovso.news.search.SearchActivity;
import io.github.ovso.news.search.SearchActivityModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton
  @ContributesAndroidInjector(modules = { MainActivityModule.class })
  abstract MainActivity bindMainActivity();

  @Singleton
  @ContributesAndroidInjector(modules = { ListUpActivityModule.class })
  abstract ListUpActivity bindListUpActivity();

  @Singleton
  @ContributesAndroidInjector(modules = { SearchActivityModule.class })
  abstract SearchActivity bindSearchActivity();
}