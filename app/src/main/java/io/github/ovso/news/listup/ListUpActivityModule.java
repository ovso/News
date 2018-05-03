package io.github.ovso.news.listup;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;

@Module
public class ListUpActivityModule {
    @Singleton
    @Provides
    ListUpPresenter provideView(ListUpPresenter.View view, AppDatabase database) {
        return new ListUpPresenterImpl(view, database);
    }
}
