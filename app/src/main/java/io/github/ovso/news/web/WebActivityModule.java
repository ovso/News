package io.github.ovso.news.web;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import javax.inject.Singleton;

@Module public class WebActivityModule {

  @Singleton @Provides
  public WebPresenter provideMainPresenter(WebPresenter.View view, AppDatabase database, SchedulersFacade schedulers) {
    return new WebPresenterImpl(view, database, schedulers);
  }
}
