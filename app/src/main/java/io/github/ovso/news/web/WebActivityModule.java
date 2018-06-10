package io.github.ovso.news.web;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.web.apdater.WebAdapterDataModel;
import io.github.ovso.news.web.apdater.WebPagerAdapter;
import javax.inject.Singleton;

@Module public class WebActivityModule {

  @Singleton @Provides
  public WebPresenter provideMainPresenter(WebPresenter.View view, AppDatabase database,
      SchedulersFacade schedulers, WebAdapterDataModel<Fragment> adapterDataModel) {
    return new WebPresenterImpl(view, database, schedulers, adapterDataModel);
  }

  @Singleton @Provides public WebPagerAdapter provideWebPagerAdapter(FragmentManager manager) {
    return new WebPagerAdapter(manager);
  }

  @Provides public FragmentManager provideFragmentPagerAdapter(WebActivity activity) {
    return activity.getSupportFragmentManager();
  }

  @Provides public BaseAdapterView provideAdapterView(WebPagerAdapter adapter) {
    return adapter;
  }

  @Provides public WebAdapterDataModel<Fragment> provideAdapterDataModel(WebPagerAdapter adapter) {
    return adapter;
  }
}
