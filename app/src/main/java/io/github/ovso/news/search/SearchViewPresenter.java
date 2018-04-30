package io.github.ovso.news.search;

public interface SearchViewPresenter {

  void onCreate();

  boolean onQueryTextChange(String newText);

  interface View {

    void setListener();
  }
}
