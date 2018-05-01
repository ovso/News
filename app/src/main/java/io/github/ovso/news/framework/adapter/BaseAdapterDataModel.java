package io.github.ovso.news.framework.adapter;

import java.util.List;

/**
 * Created by jaeho on 2017. 8. 1
 */

public interface BaseAdapterDataModel<T> {
  void add(T item);
  void addAll(List<T> all);
  T remove(int position);
  T getItem(int position);
  void add(int index, T item);
  int getSize();

  void clear();
}
