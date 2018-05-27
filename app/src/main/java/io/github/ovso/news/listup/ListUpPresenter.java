package io.github.ovso.news.listup;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;

import java.util.List;

import io.github.ovso.news.db.WebsiteEntity;

public interface ListUpPresenter {

    void onCreate();

    void onDestroy();

    void onItemDragFinished();

    boolean onItemLongClick(int position);

    interface View {
        void showErrorMessage(@StringRes int resId);

        void setupRecyclerView();

        void refresh();

        Context getContext();

        void release();

        void showSnackBar(@StringRes int resId);

        void showRemoveDialog(String title, DialogInterface.OnClickListener onClickListener);

        void notifyItemRemoved(int position);
    }
}
