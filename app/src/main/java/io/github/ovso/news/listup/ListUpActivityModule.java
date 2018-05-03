package io.github.ovso.news.listup;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import javax.inject.Singleton;

@Module
public class ListUpActivityModule {
  @Singleton
  @Provides
  public ListUpPresenter provideListUpPresenter(ListUpPresenter.View view, AppDatabase
      database, SchedulersFacade schedulers, BaseAdapterDataModel<WebsiteEntity>
      adapterDataModel) {
    return new ListUpPresenterImpl(view, database, schedulers, adapterDataModel);
  }

  @Singleton
  @Provides
  public ListUpAdapter provideListUpAdapter(OnRecyclerItemClickListener<WebsiteEntity> listener) {
    return new ListUpAdapter.Builder().setOnItemClickListener(listener).build();
  }

  @Provides
  public BaseAdapterView provideAdapterView(ListUpAdapter adapter) {
    return adapter;
  }

  @Provides
  public BaseAdapterDataModel<WebsiteEntity> provideAdapterDataModel(ListUpAdapter adapter) {
    return adapter;
  }
}
