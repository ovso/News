package io.github.ovso.news.framework.utils;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public class AssetsUtils {

  public static String read(Context context, String fileName)
      throws IOException {
    AssetManager assetManager = context.getResources().getAssets();
    String json;
    InputStream is = assetManager.open(fileName);
    byte[] buffer = new byte[is.available()];
    is.read(buffer);
    is.close();
    json = new String(buffer, "UTF-8");
    return json;
  }
}
