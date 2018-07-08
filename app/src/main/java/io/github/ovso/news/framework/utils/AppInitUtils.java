package io.github.ovso.news.framework.utils;

import android.content.Context;
import android.content.ContextWrapper;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.news.Security;
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

  public static void prefs(Context context) {
    new Prefs.Builder()
        .setContext(context)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(context.getPackageName())
        .setUseDefaultSharedPreference(true)
        .build();
  }

  public static void admob(Context context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.get());
  }
}
