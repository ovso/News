package io.github.ovso.news.listup;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.listup.listener.OnPerformDeleteActionListener;
import javax.inject.Singleton;

@Module
public class ListUpActivityModule {
  @Singleton
  @Provides
  public ListUpPresenter provideListUpPresenter(ListUpPresenter.View view, AppDatabase
      database, BaseAdapterDataModel<WebsiteEntity>
      adapterDataModel) {
    return new ListUpPresenterImpl(view, database, adapterDataModel);
  }

  @Singleton
  @Provides
  public ListUpAdapter provideListUpAdapter(
      OnRecyclerItemClickListener<WebsiteEntity> itemClickListener,
      OnPerformDeleteActionListener deleteActionListener) {
    return new ListUpAdapter.Builder().setOnItemClickListener(itemClickListener)
        .setOnPerformDeleteActionListener(deleteActionListener)
        .build();
  }

  @Provides
  public BaseAdapterView provideAdapterView(ListUpAdapter adapter) {
    return adapter;
  }

  @Provides
  public BaseAdapterDataModel<WebsiteEntity> provideAdapterDataModel(ListUpAdapter adapter) {
    return adapter;
  }

  @Singleton @Provides public RecyclerViewSwipeManager provideRecyclerViewSwipeManager() {
    return new RecyclerViewSwipeManager();
  }
}
