package io.github.ovso.news.listup;

import android.arch.lifecycle.LifecycleOwner;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.news.R;
import io.github.ovso.news.data.Preferences;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.resources.ResourcesProvider;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Collections;
import java.util.List;
import timber.log.Timber;

public class ListUpPresenterImpl implements ListUpPresenter {

  private ListUpPresenter.View view;
  private AppDatabase database;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<WebsiteEntity> adapterDataModel;
  private SchedulersFacade schedulers;
  private ResourcesProvider resourcesProvider;

  ListUpPresenterImpl(ListUpPresenter.View view, AppDatabase database,
      BaseAdapterDataModel<WebsiteEntity> adapterDataModel, SchedulersFacade schedulers,
      ResourcesProvider $resourcesProvider) {
    this.view = view;
    this.database = database;
    this.adapterDataModel = adapterDataModel;
    this.schedulers = schedulers;
    resourcesProvider = $resourcesProvider;
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
      compositeDisposable.add(
          Observable.fromCallable(() -> database.insertFirstRunData(view.getContext()))
              .subscribeOn(schedulers.io())
              .subscribe(b -> Timber.d("result = " + b.booleanValue()),
                  throwable -> Timber.d(throwable))
      );
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

  @Override public void onRecyclerViewScrolled(int dy) {
    if (dy > 0) {
      view.hideFab();
    } else if (dy < 0) {
      view.showFab();
    }
  }

  @Override public void onFabItemClick(int id) {
    switch (id) {
      case R.id.fab_item_search:
        view.hideFabSheet();
        view.navigateToSearch();
        break;
      case R.id.fab_item_help:
        view.hideFabSheet();
        view.showHelpDialog(getHelpMessage());
        break;
      case R.id.fab_item_license:
        view.showOpenSourceLicensesDialog();
        break;
    }
  }

  private String getHelpMessage() {
    return resourcesProvider.string(R.string.help_msg_1) + "\n\n"
        + resourcesProvider.string(R.string.help_msg_2) + "\n\n"
        + resourcesProvider.string(R.string.help_msg_3) + "\n\n"
        + resourcesProvider.string(R.string.help_msg_4);
  }
}
