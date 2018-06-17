package io.github.ovso.news.framework.baseview;

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
import io.github.ovso.news.ad.MyAdView;

public abstract class BaseActivity extends DaggerAppCompatActivity {

  private Unbinder unbinder;
  protected @BindView(R.id.root_view) ViewGroup rootView;
  protected @BindView(R.id.ad_container) ViewGroup adContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    setupButterKnife();
    setupNavigationBarColor();
    showAd();
  }

  private void showAd() {
    adContainer.addView(MyAdView.getAdmobAdView(getApplicationContext()));
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
