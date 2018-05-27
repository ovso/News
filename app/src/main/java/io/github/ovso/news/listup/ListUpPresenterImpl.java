package io.github.ovso.news.listup;

import android.arch.lifecycle.LifecycleOwner;

import java.util.Collections;
import java.util.List;

import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.Observable;
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
        database.websiteDao().getLiveDataItems().observe((LifecycleOwner) view.getContext(), $items -> compositeDisposable.addAll(Observable.fromCallable(() -> {
            final List<WebsiteEntity> items = $items;
            Collections.sort(items, (o1, o2) -> o1.position < o2.position ? -1 : o1.position > o2.position ? 1 : 0);
            return items;
        }).subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(entities -> {
            adapterDataModel.clear();
            view.refresh();
            adapterDataModel.addAll(entities);
            view.refresh();
        })));

    }

    @Override
    public void onDestroy() {
        //database.close();
        compositeDisposable.clear();
        view.release();
    }

    @Override
    public void onItemDragFinished() {
        updateItemPositionOnDatabase();
    }

    private void updateItemPositionOnDatabase() {
        List<WebsiteEntity> items = adapterDataModel.getItems();
        for (int i = 0; i < items.size(); i++) {
            WebsiteEntity item = items.get(i);
            item.position = i;
            database.websiteDao().update(item);
        }
    }

    @Override
    public boolean onItemLongClick(int position) {
        String title = adapterDataModel.getItem(position).getTitle();
        view.showRemoveDialog(String.valueOf(DeprecatedUtils.fromHtml(title)), (dialog, which) -> {
            if (which == -1) { // -2 cancel -1 ok
                WebsiteEntity item = adapterDataModel.remove(position);
                view.notifyItemRemoved(position);
                database.websiteDao().delete(item);
                updateItemPositionOnDatabase();
                view.showSnackBar(R.string.delete_it);
            }
            dialog.dismiss();
        });
        return true;
    }
}
