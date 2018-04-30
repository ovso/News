package io.github.ovso.news.web;

public class WebPresenterImpl implements WebPresenter {

  private WebPresenter.View view;

  public WebPresenterImpl(WebPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
  }
}
