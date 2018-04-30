package io.github.ovso.news.framework;

import android.content.Context;
import timber.log.Timber;

public class AppInitUtils {

  public static void timber(boolean debug) {
    if (debug) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public static boolean isDebug(Context context) {
    return SystemUtils.isDebuggable(context);
  }
}
