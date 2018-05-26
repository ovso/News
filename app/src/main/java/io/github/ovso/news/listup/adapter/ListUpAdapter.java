package io.github.ovso.news.listup.adapter;

import android.view.View;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import io.github.ovso.news.R;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.DeprecatedUtils;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.listup.listener.OnMoveItemListener;
import lombok.Setter;
import lombok.experimental.Accessors;

public class ListUpAdapter extends BaseDraggableAdapter
        implements BaseAdapterDataModel<WebsiteEntity>, BaseAdapterView,
        DraggableItemAdapter<ListUpViewHolder> {
    private List<WebsiteEntity> items = new ArrayList<>();
    private OnRecyclerItemClickListener<WebsiteEntity> onItemClickListener;
    private OnMoveItemListener onMoveItemListener;

    private ListUpAdapter(ListUpAdapter.Builder builder) {
        setHasStableIds(true); // this is required for D&D feature.
        onItemClickListener = builder.onItemClickListener;
        onMoveItemListener = builder.onMoveItemListener;
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
            holder.descTextView.setText(item.getDescription());
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
        }
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId(); // need to return stable (= not change even after reordered) value
    }

    @Override
    public void add(WebsiteEntity item) {

    }

    @Override
    public void addAll(List<WebsiteEntity> $items) {
        items.addAll($items);
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
        //items.add(index, item);
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

    @Override
    public boolean onCheckCanStartDrag(ListUpViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(ListUpViewHolder holder, int position) {
        return null;
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        WebsiteEntity movedItem = items.remove(fromPosition);
        items.add(toPosition, movedItem);
        onMoveItemListener.onMoveItem(items);
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    @Override
    public void onItemDragStarted(int position) {

    }

    @Override
    @DebugLog  public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {

    }

    @Accessors(chain = true)
    @Setter
    public static class Builder {
        private OnRecyclerItemClickListener<WebsiteEntity> onItemClickListener;
        private OnMoveItemListener onMoveItemListener;
        public ListUpAdapter build() {
            return new ListUpAdapter(this);
        }
    }

}
