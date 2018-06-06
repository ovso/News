package io.github.ovso.news.web;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.web.listener.OnWebNavigationListener;
import io.github.ovso.news.web.listener.OnWebViewStatusListener;
import io.github.ovso.news.web.apdater.WebPagerAdapter;
import io.github.ovso.news.web.frag.WebFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class WebActivity extends BaseActivity implements WebPresenter.View,
    OnWebViewStatusListener {
  @BindView(R.id.viewpager) WebViewPager viewPager;
  @BindView(R.id.left_button) View leftButton;
  @BindView(R.id.right_button) View rightButton;
  @BindView(R.id.progressbar) ContentLoadingProgressBar progressBar;
  @Inject
  WebPresenter presenter;
  private OnWebNavigationListener onWebNavigationListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate(getIntent());
  }

  @OnClick({
      R.id.back_button, R.id.forward_button, R.id.refresh_button, R.id.share_button,
      R.id.listup_button
  }) void onNaviClick(View view) {
    presenter.onNaviClick(view.getId());
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_web;
  }

  @Override public void setupViewPager(List<WebsiteEntity> items) {
    List<Fragment> fragments = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      Bundle args = new Bundle();
      args.putString("link", items.get(i).getLink());
      args.putInt("position", i);
      fragments.add(WebFragment.newInstance(args));
    }
    viewPager.setAdapter(new WebPagerAdapter(getSupportFragmentManager(), fragments));
    viewPager.setPagingEnabled(false);
  }

  @OnPageChange(R.id.viewpager) void onPageChange(int position) {
    presenter.onPageChange(position, viewPager.getAdapter().getCount());
    onWebNavigationListener = getOnWebNavigationListener(position);
  }

  private OnWebNavigationListener getOnWebNavigationListener(int position) {
    Fragment item = ((WebPagerAdapter) viewPager.getAdapter()).getItem(position);
    return (OnWebNavigationListener) item;
  }

  @Override public void navigateToPosition(int position) {
    viewPager.setCurrentItem(position, true);
  }

  @Override public void hideLeftButton() {
    leftButton.setVisibility(View.GONE);
  }

  @Override public void hideRightButton() {
    rightButton.setVisibility(View.GONE);
  }

  @Override public void showLeftButton() {
    leftButton.setVisibility(View.VISIBLE);
  }

  @Override public void showRightButton() {
    rightButton.setVisibility(View.VISIBLE);
  }

  @Override public void unlockPaging() {
    viewPager.setPagingEnabled(true);
  }

  @Override public void navigateToListUp() {
    ActivityUtils.startActivityListUp(this);
  }

  @Override public void moveToBackOnWeb() {
    onWebNavigationListener.onBack();
  }

  @Override public void moveToForwardOnWeb() {
    onWebNavigationListener.onForward();
  }

  @Override public void reloadOnWeb() {
    onWebNavigationListener.onReload();
  }

  @Override public void shareOnWeb() {
    onWebNavigationListener.onShare();
  }

  @Override public Context getContext() {
    return this;
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @OnClick(R.id.left_button) void onLeftClick() {
    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
  }

  @OnClick(R.id.right_button) void onRightClick() {
    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
  }

  @Override public void onProgress(int progress, int itemPosition) {
    if (viewPager.getCurrentItem() == itemPosition) {
      progressBar.setProgress(progress);
    }
  }

  @Override public void onPageStarted(int itemPosition) {
    if (viewPager.getCurrentItem() == itemPosition) {
      progressBar.show();
    }
  }

  @Override public void onPageFinished(int itemPosition) {
    if (viewPager.getCurrentItem() == itemPosition) {
      progressBar.hide();
    }
  }
}