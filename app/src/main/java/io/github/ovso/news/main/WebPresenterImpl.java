package io.github.ovso.news.main;

public class WebPresenterImpl implements WebPresenter {

  private WebPresenter.View view;

  public WebPresenterImpl(WebPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
  }
}
