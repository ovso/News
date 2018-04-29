package io.github.ovso.news.main;

import io.github.ovso.news.R;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;

  public MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
    view.setListener();
  }

  @Override public boolean onNavigationItemSelected(int itemId) {
    int id = itemId;

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    return true;
  }
}
