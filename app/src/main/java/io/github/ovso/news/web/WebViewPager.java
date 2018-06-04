package io.github.ovso.news.web;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import lombok.Getter;
import lombok.Setter;

public class WebViewPager extends ViewPager {
  @Getter @Setter private boolean isPagingEnabled = true; // is swipe enable

  public WebViewPager(@NonNull Context context) {
    super(context);
  }

  public WebViewPager(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return this.isPagingEnabled && super.onTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
  }
}
