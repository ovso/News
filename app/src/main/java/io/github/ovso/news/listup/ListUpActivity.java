package io.github.ovso.news.listup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

import io.github.ovso.news.web.WebActivity;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.utils.ActivityUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.baseview.BaseActivity;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.listup.listener.OnAdapterListener;
import timber.log.Timber;

public class ListUpActivity extends BaseActivity
    implements ListUpPresenter.View, OnAdapterListener<WebsiteEntity> {

  @BindView(R.id.recyclerview)
  RecyclerView recyclerView;
  @BindView(R.id.sheet_fab) SheetFab sheetFab;
  @BindView(R.id.fab_sheet_view) View fabSheetView;
  @BindView(R.id.fab_overlay_view) View fabOverlayView;
  @Inject
  ListUpPresenter presenter;
  @Inject
  ListUpAdapter adapter;
  @Inject
  BaseAdapterView adapterView;
  @Inject
  RecyclerViewDragDropManager dragDropManager;
  @Inject
  DraggableItemAnimator animator;
  @Inject
  ItemShadowDecorator itemShadowDecorator;
  @Inject
  SimpleListDividerDecorator simpleListDividerDecorator;
  @Inject
  RecyclerView.Adapter wrappedAdapter;
  @Inject
  LinearLayoutManager layoutManager;
  private MaterialSheetFab materialSheetFab;
  private int statusBarColor;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
    setupFab();
  }

  private void setupFab() {
    int sheetColor = getResources().getColor(android.R.color.white);
    int fabColor = getResources().getColor(R.color.colorAccent);

    // Create material sheet FAB
    materialSheetFab =
        new MaterialSheetFab<>(sheetFab, fabSheetView, fabOverlayView, sheetColor, fabColor);

    // Set material sheet event listener
    materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
      @Override
      public void onShowSheet() {
        // Save current status bar color
        //statusBarColor = getStatusBarColor();
        // Set darker status bar color to match the dim overlay
        //setStatusBarColor(getResources().getColor(R.color.theme_primary_dark2));
      }

      @Override
      public void onHideSheet() {
        // Restore status bar color
        //setStatusBarColor(statusBarColor);
      }
    });
  }

  @OnClick({
      R.id.fab_item_search, R.id.fab_item_help, R.id.fab_item_license
  }) void onFabItemClick(View view) {
    presenter.onFabItemClick(view.getId());
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_listup;
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
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(wrappedAdapter);
    recyclerView.setItemAnimator(animator);
    recyclerView.addItemDecoration(itemShadowDecorator);
    recyclerView.addItemDecoration(simpleListDividerDecorator);
    recyclerView.addOnScrollListener(onScrollListener);
    dragDropManager.attachRecyclerView(recyclerView);
  }

  private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);
      presenter.onRecyclerViewScrolled(dy);
    }
  };

  @Override
  public void refresh() {
    adapterView.refresh();
  }

  @Override
  public Context getContext() {
    return this;
  }

  @Override
  public void release() {
    if (dragDropManager != null) {
      dragDropManager.release();
      dragDropManager = null;
    }

    if (recyclerView != null) {
      recyclerView.setItemAnimator(null);
      recyclerView.setAdapter(null);
      recyclerView = null;
    }

    if (wrappedAdapter != null) {
      WrapperAdapterUtils.releaseAll(wrappedAdapter);
      wrappedAdapter = null;
    }
    adapter = null;
    layoutManager = null;
  }

  @Override
  public void showSnackBar(int resId) {
    Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
  }

  @Override
  public void showRemoveDialog(String title, DialogInterface.OnClickListener onClickListener) {
    new AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(R.string.do_you_want_to_delete)
        .setPositiveButton(android.R.string.ok, onClickListener)
        .setNegativeButton(android.R.string.cancel, onClickListener)
        .show();
  }

  @Override
  public void notifyItemRemoved(int position) {
    adapterView.notifyItemRemoved(position);
  }

  @DebugLog
  @Override
  public void onItemClick(WebsiteEntity item) {
    presenter.onItemClick(item);
  }

  @Override public void navigateToWeb(int position) {
    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
    intent.putExtra("position", position);
    startActivity(intent);
  }

  @Override public void changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar);
  }

  @Override public void hideFab() {
    //fab.hide();
    sheetFab.hide();
    Timber.d("hideFab");
  }

  @Override public void showFab() {
    //fab.show();
    sheetFab.show();
    Timber.d("showFab");
  }

  @Override public void hideFabSheet() {
    materialSheetFab.hideSheet();
  }

  @Override public void navigateToSearch() {
    ActivityUtils.startActivitySearch(this);
  }

  @Override public void showHelpDialog(String msg) {
    new AlertDialog.Builder(getContext()).setTitle(R.string.help).setMessage(msg).setPositiveButton(
        android.R.string.ok, (dialog, which) -> {
          dialog.dismiss();
        }).show();
  }

  @Override public void showOpenSourceLicensesDialog() {
    // do...
  }

  @Override
  public boolean onItemLongClick(int position) {
    return presenter.onItemLongClick(position);
  }

  @Override
  protected void onPause() {
    dragDropManager.cancelDrag();
    super.onPause();
  }

  @Override
  public void onItemDragFinished() {
    presenter.onItemDragFinished();
  }
}
