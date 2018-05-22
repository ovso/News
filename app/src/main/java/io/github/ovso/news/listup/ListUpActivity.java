package io.github.ovso.news.listup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.ActivityUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.listup.listener.OnMoveItemListener;

public class ListUpActivity extends BaseActivity implements ListUpPresenter.View,
        OnRecyclerItemClickListener<WebsiteEntity>, OnMoveItemListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject
    ListUpPresenter presenter;
    @Inject
    ListUpAdapter adapter;
    @Inject
    BaseAdapterView adapterView;
    @Inject
    RecyclerViewDragDropManager dragDropManager;


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
        recyclerView.setAdapter(dragDropManager.createWrappedAdapter(adapter));
        dragDropManager.attachRecyclerView(recyclerView);
    }

    @Override
    public void refresh() {
        adapterView.refresh();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @DebugLog
    @Override
    public void onItemClick(WebsiteEntity item) {
        ActivityUtils.startActivityWeb(this);
    }

    @Override
    public void onMoveItem(WebsiteEntity moveItem, int fromPosition, int toPosition) {
        presenter.onMoveItem(moveItem, fromPosition, toPosition);
    }
}
