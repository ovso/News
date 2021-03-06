package io.github.ovso.news.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnTextChanged;
import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.framework.listener.OnSimpleQueryTextListener;
import io.github.ovso.news.search.adapter.SearchAdapter;
import io.github.ovso.news.search.adapter.WrapperLinearLayoutManager;
import io.github.ovso.news.search.model.Website;
import javax.inject.Inject;

public class SearchActivity extends BaseActivity implements SearchViewPresenter.View,
    OnRecyclerItemClickListener<Website> {
  @BindView(R.id.recyclerview) RecyclerView recyclerView;
  @BindView(R.id.search_edittext) EditText searchEditText;

  @Inject SearchViewPresenter presenter;
  @Inject SearchAdapter adapter;
  @Inject BaseAdapterView adapterView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_search;
  }

  @OnTextChanged(R.id.search_edittext) void onSearchTextChanged(CharSequence sequence) {
    presenter.onSearchTextChanged(sequence.toString());
  }

  @Override public void showErrorMessage(int resId) {
    Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showMessage(int resId) {
    Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showMessage(String msg) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();
  }

  @Override protected void onStop() {
    presenter.onStop();
    super.onStop();
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new WrapperLinearLayoutManager(getApplicationContext()));
    recyclerView.setAdapter(adapter);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void allRefresh() {
    adapter.allRefresh();
  }

  @Override public void onItemClick(Website item) {
    presenter.onItemClick(item);
  }
}