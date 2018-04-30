package io.github.ovso.news.web;

import android.os.Bundle;
import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.BaseActivity;
import javax.inject.Inject;

public class WebActivity extends BaseActivity implements WebPresenter.View {
  @Inject
  WebPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @OnClick(R.id.fab) void onFabClick() {
    ActivityUtils.startActivityListUp(this);
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }
}