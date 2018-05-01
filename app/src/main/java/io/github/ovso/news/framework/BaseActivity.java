package io.github.ovso.news.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.news.R;

/**
 * Created by jaeho on 2017. 7. 31
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

  private Unbinder unbinder;
  protected @BindView(R.id.root_view) ViewGroup rootView;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    setupButterKnife();
    setupNavigationBarColor();
  }

  private void setupButterKnife() {
    unbinder = ButterKnife.bind(this);
  }

  private void setupNavigationBarColor() {
    getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
  }

  @LayoutRes protected abstract int getLayoutResId();

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }
}
