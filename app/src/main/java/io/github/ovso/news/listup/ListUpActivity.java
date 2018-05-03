package io.github.ovso.news.listup;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.BaseActivity;

public class ListUpActivity extends BaseActivity implements ListUpPresenter.View {

    @Inject
    ListUpPresenter presenter;

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
}
