package io.github.ovso.news.listup.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import io.github.ovso.news.R;

public class ListUpViewHolder extends BaseDraggableAdapter.BaseViewHolder {
  @BindView(R.id.title_textview) TextView titleTextView;
  @BindView(R.id.desc_textview) TextView descTextView;
  @BindView(R.id.root_view) View rootView;
  public ListUpViewHolder(View itemView) {
    super(itemView);
  }

}