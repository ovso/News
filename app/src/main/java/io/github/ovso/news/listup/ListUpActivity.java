package io.github.ovso.news.listup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.BaseActivity;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import javax.inject.Inject;

public class ListUpActivity extends BaseActivity implements ListUpPresenter.View,
    OnRecyclerItemClickListener<WebsiteEntity> {

  @BindView(R.id.recyclerview)
  RecyclerView recyclerView;

  @Inject ListUpPresenter presenter;
  @Inject ListUpAdapter adapter;
  @Inject BaseAdapterView adapterView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_listup;
  }

  @OnClick(R.id.fab)
  void onFabClick() {
    ActivityUtils.startActivitySearch(this);
  }

  @Override
  protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @Override
  public void showErrorMessage(int resId) {
    Snackbar.make(rootView, resId, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void refresh() {
    adapterView.refresh();
  }

  @Override public void onItemClick(WebsiteEntity item) {

  }
}
