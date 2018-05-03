package io.github.ovso.news.framework.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.db.AppDatabase;

@Module
public class AppModule {
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    public AppDatabase provideAppDatabase(Application application) {
        return AppDatabase.getInstance(application);
    }

}