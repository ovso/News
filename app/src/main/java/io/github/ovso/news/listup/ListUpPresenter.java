package io.github.ovso.news.listup;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import io.github.ovso.news.db.WebsiteEntity;

public interface ListUpPresenter {

  void onCreate();

  void onDestroy();

  void onItemDragFinished();

  boolean onItemLongClick(int position);

  void onItemClick(WebsiteEntity item);

  void onRecyclerViewScrolled(int dy, int visibility);

  interface View {
    void showErrorMessage(@StringRes int resId);

    void setupRecyclerView();

    void refresh();

    Context getContext();

    void release();

    void showSnackBar(@StringRes int resId);

    void showRemoveDialog(String title, DialogInterface.OnClickListener onClickListener);

    void notifyItemRemoved(int position);

    void navigateToWeb(int position);

    void finish();

    void changeTheme();

    void hideFab();

    void showFab();
  }
}
