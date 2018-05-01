package io.github.ovso.news.search.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import io.github.ovso.news.R;
import io.github.ovso.news.framework.adapter.BaseRecyclerAdapter;

public class SearchViewHolder extends BaseRecyclerAdapter.BaseViewHolder {
  @BindView(R.id.title_textview) TextView titleTextView;

  public SearchViewHolder(View itemView) {
    super(itemView);
  }
}