package io.github.ovso.news.web.apdater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import java.util.ArrayList;
import java.util.List;

public class WebPagerAdapter extends FragmentPagerAdapter implements WebAdapterDataModel<Fragment>,
    BaseAdapterView {
  private List<Fragment> items = new ArrayList<>();

  public WebPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    //super.destroyItem(container, position, object);
  }

  @Override public void addAll(List<Fragment> $items) {
    items.addAll($items);
  }

  @Override public Fragment getItem(int position) {
    return items.get(position);
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public int getCount() {
    return getSize();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }

  @Override public void notifyItemRemoved(int position) {
    // do nothing..
  }
}
