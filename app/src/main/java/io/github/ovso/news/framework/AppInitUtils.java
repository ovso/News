package io.github.ovso.news.framework;

import android.content.Context;
import com.facebook.stetho.Stetho;
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

  public static void stetho(Context context) {
    Stetho.initializeWithDefaults(context);
  }
}
