package io.github.ovso.news.listup;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.BaseActivity;

public class ListUpActivity extends BaseActivity {

  @Override protected int getLayoutResId() {
    return R.layout.activity_listup;
  }

  @OnClick(R.id.fab) void onFabClick() {
    ActivityUtils.startActivitySearch(this);
  }
}
