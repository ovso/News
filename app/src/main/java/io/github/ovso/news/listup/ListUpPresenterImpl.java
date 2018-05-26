package io.github.ovso.news.listup;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;

public class ListUpPresenterImpl implements ListUpPresenter {

  private ListUpPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<WebsiteEntity> adapterDataModel;
  private SchedulersFacade schedulers;
  ListUpPresenterImpl(ListUpPresenter.View view, AppDatabase database,
      BaseAdapterDataModel<WebsiteEntity> adapterDataModel, SchedulersFacade schedulers) {
    this.view = view;
    this.database = database;
    this.adapterDataModel = adapterDataModel;
    this.schedulers = schedulers;
  }

  @Override
  public void onCreate() {
    view.setupRecyclerView();

    database.websiteDao().getAll().observe((LifecycleOwner) view.getContext(), new Observer<List<WebsiteEntity>>() {
      @Override
      public void onChanged(@Nullable List<WebsiteEntity> $items) {
        final List<WebsiteEntity> items = $items;
        Collections.sort(items, new Comparator<WebsiteEntity>() {
          @Override
          public int compare(WebsiteEntity o1, WebsiteEntity o2) {
            //return o1.position > o2.position ? 1 : 0;
            return o1.position < o2.position ? -1 : o1.position > o2.position ? 1:0;

          }
        });
        adapterDataModel.clear();
        view.refresh();
        adapterDataModel.addAll(items);
        view.refresh();
      }
    });

  }

  @Override
  public void onDestroy() {
    compositeDisposable.clear();
  }


    @Override
    public void onMoveItem(List<WebsiteEntity> items) {
        for (int i = 0; i < items.size(); i++) {
            WebsiteEntity item = items.get(i);
            item.position = i;
            database.websiteDao().update(item);
        }
    }
}
