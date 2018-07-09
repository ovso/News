package io.github.ovso.news.framework.resources;

import android.content.Context;
import android.content.res.Resources;
import javax.inject.Inject;

public class ResourcesProviderImpl implements ResourcesProvider {

  private Resources resources;

  @Inject ResourcesProviderImpl(Context context) {
    resources = context.getResources();
  }

  @Override public String string(int id) {
    return resources.getString(id);
  }

  @Override public String string(int id, Object... formatArgs) {
    return resources.getString(id, formatArgs);
  }

  @Override public float dimen(int id) {
    return resources.getDimension(id);
  }
}
