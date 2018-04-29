package io.github.ovso.news.framework;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.news.R;

/**
 * Created by jaeho on 2017. 7. 31
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

  protected @BindView(R.id.toolbar) Toolbar toolbar;
  private Unbinder unbinder;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    unbinder = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    setNavigationBarColor();

  }

  private void setNavigationBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }
  }

  @LayoutRes protected abstract int getLayoutResId();

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  protected Toolbar getToolbar() {
    return toolbar;
  }
}
