package io.github.ovso.news.framework;

import android.app.Activity;
import android.content.Intent;
import io.github.ovso.news.listup.ListUpActivity;
import io.github.ovso.news.main.MainActivity;
import io.github.ovso.news.search.SearchActivity;

public class ActivityUtils {

  public static void startActivityListUp(Activity activity) {
    Intent intent = new Intent(activity, ListUpActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  public static void startActivityMain(Activity activity) {
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  public static void startActivitySearch(Activity activity) {
    Intent intent = new Intent(activity, SearchActivity.class);
    activity.startActivity(intent);
  }
}
