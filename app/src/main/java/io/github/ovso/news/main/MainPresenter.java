package io.github.ovso.news.main;

public interface MainPresenter {

    void onCreate();

    boolean onNavigationItemSelected(int itemId);

    interface View {

        void setListener();
    }
}
