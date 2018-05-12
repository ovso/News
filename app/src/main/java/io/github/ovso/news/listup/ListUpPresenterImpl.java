package io.github.ovso.news.listup;

import android.arch.lifecycle.LifecycleOwner;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.reactivex.disposables.CompositeDisposable;

public class ListUpPresenterImpl implements ListUpPresenter {

  private ListUpPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<WebsiteEntity> adapterDataModel;

  ListUpPresenterImpl(ListUpPresenter.View view, AppDatabase database,
      BaseAdapterDataModel<WebsiteEntity> adapterDataModel) {
    this.view = view;
    this.database = database;
    this.adapterDataModel = adapterDataModel;
  }

  @Override
  public void onCreate() {
    view.setupRecyclerView();

    database.websiteDao().getAll().observe((LifecycleOwner) view.getContext(), items -> {
      adapterDataModel.clear();
      view.refresh();
      adapterDataModel.addAll(items);
      view.refresh();
    });
  }

  @Override
  public void onDestroy() {
    compositeDisposable.clear();
  }
}
