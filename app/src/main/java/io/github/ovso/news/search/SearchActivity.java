package io.github.ovso.news.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
      @Override public boolean onQueryTextChange(String newText) {
        return presenter.onQueryTextChange(newText);
      }
    });
  }
}