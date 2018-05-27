package io.github.ovso.news.search.adapter;

import android.view.View;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.BaseRecyclerAdapter;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.search.model.Website;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.experimental.Accessors;

public class SearchAdapter extends BaseRecyclerAdapter
    implements BaseAdapterDataModel<Website>, BaseAdapterView {
  private List<Website> items = new ArrayList<>();
  private OnRecyclerItemClickListener<Website> onItemClickListener;

  private SearchAdapter(SearchAdapter.Builder builder) {
    onItemClickListener = builder.onItemClickListener;
  }

  @Override protected BaseViewHolder createViewHolder(View view, int viewType) {
    return new SearchViewHolder(view);
  }

  @Override public int getLayoutRes(int viewType) {
    return R.layout.search_item;
  }

  @Override public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof SearchViewHolder) {
      SearchViewHolder holder = (SearchViewHolder) viewHolder;
      final Website item = items.get(position);
      holder.titleTextView.setText(DeprecatedUtils.fromHtml(item.getTitle()));
      holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(Website item) {
    items.add(item);
  }

  @Override public void addAll(List<Website> all) {
    items.addAll(all);
  }

  @Override public Website remove(int position) {
    return items.remove(position);
  }

  @Override public Website getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, Website item) {
    items.add(index, item);
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override
  public List<Website> getItems() {
    return items;
  }

  @Override public void refresh() {
    notifyItemRangeChanged(0, items.size());
  }

  public void allRefresh() {
    notifyDataSetChanged();
  }

  @Accessors(chain = true) @Setter public static class Builder {
    private OnRecyclerItemClickListener<Website> onItemClickListener;

    public SearchAdapter build() {
      return new SearchAdapter(this);
    }
  }
}
