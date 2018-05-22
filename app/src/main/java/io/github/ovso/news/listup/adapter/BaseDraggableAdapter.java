package io.github.ovso.news.listup.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

/**
 * Created by jaeho on 2017. 8. 1
 */

public abstract class BaseDraggableAdapter
    extends RecyclerView.Adapter<BaseDraggableAdapter.BaseViewHolder> {

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(viewType), parent, false);
    return createViewHolder(view, viewType);
  }

  protected abstract BaseViewHolder createViewHolder(View view, int viewType);

  @LayoutRes public abstract int getLayoutRes(int viewType);

  @Override public abstract void onBindViewHolder(BaseViewHolder viewHolder, int position);

  @Override public abstract int getItemCount();

  public static abstract class BaseViewHolder extends AbstractDraggableItemViewHolder {

    public BaseViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
    }
}
