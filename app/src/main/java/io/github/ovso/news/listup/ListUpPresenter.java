package io.github.ovso.news.listup;

import android.support.annotation.StringRes;

public interface ListUpPresenter {

    void onCreate();

    void onDestroy();

    interface View {
        void showErrorMessage(@StringRes int resId);

        void setupRecyclerView();

        void refresh();
    }
}
