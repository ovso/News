package io.github.ovso.news.listup.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.adapter.BaseRecyclerAdapter;

public class ListUpViewHolder extends BaseRecyclerAdapter.BaseViewHolder {
  @BindView(R.id.title_textview) TextView titleTextView;

  public ListUpViewHolder(View itemView) {
    super(itemView);
  }
}