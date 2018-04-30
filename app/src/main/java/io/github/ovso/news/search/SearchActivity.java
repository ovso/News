package io.github.ovso.news.search;

import io.github.ovso.news.R;
import io.github.ovso.news.framework.BaseActivity;

public class SearchActivity extends BaseActivity implements SearchViewPresenter.View {

  @Override protected int getLayoutResId() {
    return R.layout.activity_search;
  }
}