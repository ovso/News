package io.github.ovso.news.framework;

public abstract class BasePresenter<T> {
  protected T view;

  public BasePresenter(T view) {
    this.view = view;
  }
}
