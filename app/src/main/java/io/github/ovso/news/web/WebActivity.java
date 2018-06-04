package io.github.ovso.news.web;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;
import hugo.weaving.DebugLog;
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
  @BindView(R.id.viewpager) WebViewPager viewPager;
  @BindView(R.id.left_button) View leftButton;
  @BindView(R.id.right_button) View rightButton;
  @BindView(R.id.paging_lock_button) FloatingActionButton pagingLockButton;
  @Inject
  WebPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate(getIntent());
  }

  @OnClick(R.id.fab) void onFabClick() {
    ActivityUtils.startActivityListUp(this);
  }

  @OnClick(R.id.paging_lock_button)
  void onPagingLockClick() {
    presenter.onPagingLockClick(viewPager.isPagingEnabled());
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_web;
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

  @DebugLog @OnPageChange(R.id.viewpager) void onPageChange(int position) {
    presenter.onPageChange(position, viewPager.getAdapter().getCount());
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

  @Override public void lockPaging() {
    viewPager.setPagingEnabled(false);
  }

  @Override public void showPagingLockIcon() {
    pagingLockButton.setImageResource(R.drawable.ic_lock);
  }

  @Override public void showPagingLockNotiDialog() {
    new AlertDialog.Builder(this).setTitle("!")
        .setMessage(R.string.paging_lock)
        .setPositiveButton(
            android.R.string.ok, (dialog, which) -> dialog.dismiss())
        .setNeutralButton(R.string.do_not_look_again,
            (dialog, which) -> dialog.dismiss())
        .show();
  }

  @Override public void showPagingUnlockIcon() {
    pagingLockButton.setImageResource(R.drawable.ic_lock_open);
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
}