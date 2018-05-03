package io.github.ovso.news.listup;

import java.util.List;

import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class ListUpPresenterImpl implements ListUpPresenter {

    private ListUpPresenter.View view;
    private AppDatabase database;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ListUpPresenterImpl(ListUpPresenter.View view, AppDatabase database) {
        this.view = view;
        this.database = database;
    }

    @Override
    public void onCreate() {
        List<WebsiteEntity> all = database.websiteDao().getAll();
        Timber.d("all = " + all);
        Timber.d("all size = " + all.size());
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
