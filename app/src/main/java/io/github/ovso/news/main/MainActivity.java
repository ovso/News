package io.github.ovso.news.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {
  @BindView(R.id.nav_view) NavigationView nav;
  @BindView(R.id.drawer_layout) DrawerLayout drawer;

  @Inject
  MainPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override public void setListener() {
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string
        .navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    nav.setNavigationItemSelectedListener(this);
  }

  @OnClick(R.id.fab) void onFabClick(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    drawer.closeDrawer(GravityCompat.START);
    return presenter.onNavigationItemSelected(item.getItemId());
  }
}
