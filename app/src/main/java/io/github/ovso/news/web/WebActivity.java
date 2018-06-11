package io.github.ovso.news.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.web.apdater.WebPagerAdapter;
import io.github.ovso.news.web.listener.OnWebViewStatusListener;
import javax.inject.Inject;

public class WebActivity extends BaseActivity implements WebPresenter.View,
    OnWebViewStatusListener {
  @BindView(R.id.viewpager) WebViewPager viewPager;
  @BindView(R.id.left_button) View leftButton;
  @BindView(R.id.right_button) View rightButton;
  @BindView(R.id.progressbar) ContentLoadingProgressBar progressBar;

  @Inject WebPagerAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @Inject WebPresenter presenter;

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

  @Override public void setupViewPager() {
    viewPager.setAdapter(adapter);
    viewPager.setPagingEnabled(false);
  }

  @OnPageChange(R.id.viewpager) void onPageChange(int position) {
    presenter.onPageChange(position);
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

  @Override public void navigateToListUp() {
    ActivityUtils.startActivityListUp(this);
  }

  @Override public void gotoPageOnViewPager(int position) {
    viewPager.setCurrentItem(position, true);
  }

  @Override public void setWebProgress(int progress) {
    progressBar.setProgress(progress);
  }

  @Override public void showProgressBar() {
    progressBar.show();
  }

  @Override public void hideProgressBar() {
    progressBar.hide();
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void showMessage(int resId) {
    Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void doShare(String url) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
    intent.putExtra(Intent.EXTRA_TEXT, url);
    Intent chooser = Intent.createChooser(intent, getString(R.string.app_name));
    startActivity(chooser);
  }

  @Override public Context getContext() {
    return this;
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @OnClick(R.id.left_button) void onViewPagerLeftClick() {
    presenter.onViewPagerLeftClick();
  }

  @OnClick(R.id.right_button) void onViewPagerRightClick() {
    presenter.onViewPagerRightClick();
  }

  @Override public void onProgress(int progress, int positionOfFragment) {
    presenter.onProgress(progress, positionOfFragment);
  }

  @Override public void onPageStarted(int fragmentPosition) {
    presenter.onPageStarted(fragmentPosition);
  }

  @Override public void onPageFinished(int fragmentPosition) {
    presenter.onPageFinished(fragmentPosition);
  }
}