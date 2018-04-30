package io.github.ovso.news.framework.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import io.github.ovso.news.App;

@Component(modules = {
    AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
  void inject(App app);

  @Component.Builder interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}