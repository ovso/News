package io.github.ovso.news.web.apdater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import io.github.ovso.news.framework.adapter.BasePagerAdapter;
import java.util.List;

public class WebPagerAdapter extends BasePagerAdapter {
  public WebPagerAdapter(FragmentManager fm,
      List<Fragment> $items) {
    super(fm, $items);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    //super.destroyItem(container, position, object);
  }
}
