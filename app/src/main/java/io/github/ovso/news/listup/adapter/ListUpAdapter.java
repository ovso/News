package io.github.ovso.news.listup.adapter;

import android.view.View;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.BaseRecyclerAdapter;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.experimental.Accessors;

public class ListUpAdapter extends BaseRecyclerAdapter
    implements BaseAdapterDataModel<WebsiteEntity>, BaseAdapterView {
  private List<WebsiteEntity> items = new ArrayList<>();
  private OnRecyclerItemClickListener<WebsiteEntity> onItemClickListener;

  private ListUpAdapter(ListUpAdapter.Builder builder) {
    onItemClickListener = builder.onItemClickListener;
  }

  @Override
  protected BaseViewHolder createViewHolder(View view, int viewType) {
    return new ListUpViewHolder(view);
  }

  @Override
  public int getLayoutRes(int viewType) {
    return R.layout.listup_item;
  }

  @Override
  public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof ListUpViewHolder) {
      ListUpViewHolder holder = (ListUpViewHolder) viewHolder;
      final WebsiteEntity item = items.get(position);
      holder.titleTextView.setText(DeprecatedUtils.fromHtml(item.getTitle()));
      holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
    }
  }

  @Override
  public int getItemCount() {
    return getSize();
  }

  @Override
  public void add(WebsiteEntity item) {
    items.add(item);
  }

  @Override
  public void addAll(List<WebsiteEntity> all) {
    items.addAll(all);
  }

  @Override
  public WebsiteEntity remove(int position) {
    return items.remove(position);
  }

  @Override
  public WebsiteEntity getItem(int position) {
    return items.get(position);
  }

  @Override
  public void add(int index, WebsiteEntity item) {
    items.add(index, item);
  }

  @Override
  public int getSize() {
    return items.size();
  }

  @Override
  public void clear() {
    items.clear();
  }

  @Override
  public void refresh() {
    notifyItemRangeChanged(0, items.size());
  }

  public void allRefresh() {
    notifyDataSetChanged();
  }

  @Accessors(chain = true)
  @Setter
  public static class Builder {
    private OnRecyclerItemClickListener<WebsiteEntity> onItemClickListener;

    public ListUpAdapter build() {
      return new ListUpAdapter(this);
    }
  }
}
