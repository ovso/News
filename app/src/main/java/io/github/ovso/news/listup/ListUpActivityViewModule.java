package io.github.ovso.news.listup;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ListUpActivityViewModule {
    @Binds
    abstract ListUpPresenter.View bindView(ListUpActivity activity);
}
