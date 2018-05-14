package io.github.ovso.news.listup.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import io.github.ovso.news.R;

public class ListUpViewHolder extends BaseSwipeAdapter.BaseViewHolder {
  @BindView(R.id.title_textview) TextView titleTextView;
  @BindView(R.id.root_view) View rootView;
  public ListUpViewHolder(View itemView) {
    super(itemView);
  }

  @Override public View getSwipeableContainerView() {
    return rootView;
  }
}