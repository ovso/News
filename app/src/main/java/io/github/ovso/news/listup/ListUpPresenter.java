package io.github.ovso.news.listup;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.List;

import io.github.ovso.news.db.WebsiteEntity;

public interface ListUpPresenter {

    void onCreate();

    void onDestroy();


    void onItemDragFinished(List<WebsiteEntity> items);

    boolean onItemLongClick(WebsiteEntity item);

    interface View {
        void showErrorMessage(@StringRes int resId);

        void setupRecyclerView();

        void refresh();

        Context getContext();

        void release();
    }
}
