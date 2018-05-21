package io.github.ovso.news.framework;

import android.app.Activity;
import android.content.Intent;
import io.github.ovso.news.listup.ListUpActivity;
import io.github.ovso.news.listup2.DraggableSwipeableExampleActivity;
import io.github.ovso.news.web.WebActivity;
import io.github.ovso.news.search.SearchActivity;

public class ActivityUtils {

  public static void startActivityListUp(Activity activity) {
    Intent intent = new Intent(activity, DraggableSwipeableExampleActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  public static void startActivityWeb(Activity activity) {
    Intent intent = new Intent(activity, WebActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  public static void startActivitySearch(Activity activity) {
    Intent intent = new Intent(activity, SearchActivity.class);
    activity.startActivity(intent);
  }
}
