package io.github.ovso.news.web.apdater;

import java.util.List;

public interface WebAdapterDataModel<T> {
  void addAll(List<T> $items);

  T getItem(int position);

  int getSize();

  void clear();
}
