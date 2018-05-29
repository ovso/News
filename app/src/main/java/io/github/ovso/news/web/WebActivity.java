package io.github.ovso.news.web;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.web.apdater.WebPagerAdapter;
import io.github.ovso.news.web.frag.WebFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WebActivity extends BaseActivity implements WebPresenter.View {
  @Inject
  WebPresenter presenter;
  @BindView(R.id.viewpager) ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @OnClick(R.id.fab) void onFabClick() {
    ActivityUtils.startActivityListUp(this);
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_web;
  }

  @Override public void setupViewPager() {
    List<Fragment> items = new ArrayList<>();
    items.add(WebFragment.newInstance(null));
    items.add(WebFragment.newInstance(null));
    items.add(WebFragment.newInstance(null));
    items.add(WebFragment.newInstance(null));
    items.add(WebFragment.newInstance(null));
    viewPager.setAdapter(new WebPagerAdapter(getSupportFragmentManager(), items));
  }

  @Override public void setupViewPager(List<WebsiteEntity> items) {
    List<Fragment> fragments = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      Bundle args = new Bundle();
      args.putString("link", items.get(i).getLink());
      fragments.add(WebFragment.newInstance(args));
    }
    viewPager.setAdapter(new WebPagerAdapter(getSupportFragmentManager(), fragments));
  }

  @Override public Context getContext() {
    return this;
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }
}