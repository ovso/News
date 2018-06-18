package io.github.ovso.news.listup;

import android.arch.lifecycle.LifecycleOwner;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.news.data.Preferences;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Collections;
import java.util.List;

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
    view.changeTheme();
  }

  @Override
  public void onCreate() {
    if (!isCurrentLastScreen()) {
      view.navigateToWeb(Prefs.getInt(Preferences.KEY_WEB_POSITION.get(), 0));
      view.finish();
      return;
    }
    view.setupRecyclerView();
    database.websiteDao()
        .getLiveDataItems()
        .observe((LifecycleOwner) view.getContext(),
            $items -> compositeDisposable.addAll(Observable.fromCallable(() -> {
              final List<WebsiteEntity> items = $items;
              Collections.sort(items,
                  (o1, o2) -> o1.position < o2.position ? -1 : o1.position > o2.position ? 1 : 0);
              return items;
            }).subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(entities -> {
              adapterDataModel.clear();
              view.refresh();
              adapterDataModel.addAll(entities);
              view.refresh();
            })));

    initFirstRun();
  }

  private void initFirstRun() {
    if (Prefs.getBoolean(Preferences.KEY_FIRST_RUN.get(), true)) {
      Prefs.putBoolean(Preferences.KEY_FIRST_RUN.get(), false);
      database.insertFirstRunData(view.getContext());
    }
  }

  private boolean isCurrentLastScreen() {
    String last =
        Prefs.getString(Preferences.KEY_LAST_SCREEN.get(), Preferences.SCREEN_LIST_UP.get());
    return last.equals(Preferences.SCREEN_LIST_UP.get());
  }

  @Override
  public void onDestroy() {
    //database.close();
    compositeDisposable.clear();
    view.release();
    Prefs.putString(Preferences.KEY_LAST_SCREEN.get(), Preferences.SCREEN_LIST_UP.get());
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
      }
      dialog.dismiss();
    });
    return true;
  }

  @Override public void onItemClick(WebsiteEntity item) {
    view.navigateToWeb(item.getPosition());
    view.finish();
  }
}
