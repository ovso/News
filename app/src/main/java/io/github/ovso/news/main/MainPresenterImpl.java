package io.github.ovso.news.main;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;

  public MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
  }
}
