package io.github.ovso.news.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.BaseActivity;
import io.github.ovso.news.framework.listener.OnSimpleQueryTextListener;
import javax.inject.Inject;

public class SearchActivity extends BaseActivity implements SearchViewPresenter.View {
  @BindView(R.id.searchview) SearchView searchView;

  @Inject
  SearchViewPresenter presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_search;
  }

  @Override public void setListener() {
    searchView.setOnQueryTextListener(new OnSimpleQueryTextListener() {
      @Override public void onQueryTextChanged(String newText) {
        presenter.onQueryTextChange(newText);
      }
    });
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
}