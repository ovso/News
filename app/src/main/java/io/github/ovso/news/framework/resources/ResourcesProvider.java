package io.github.ovso.news.framework.resources;

import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;

public interface ResourcesProvider {

  String string(@StringRes int id);
  String string(@StringRes int id, Object... formatArgs);
  float dimen(@DimenRes int id);
}
