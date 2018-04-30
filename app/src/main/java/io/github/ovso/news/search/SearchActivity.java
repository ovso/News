package io.github.ovso.news.search;

import android.support.v7.widget.SearchView;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.BaseActivity;

public class SearchActivity extends BaseActivity implements SearchViewPresenter.View {
  @BindView(R.id.searchview) SearchView searchView;

  @Override protected int getLayoutResId() {
    return R.layout.activity_search;
  }
}