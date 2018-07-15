package io.github.ovso.news.listup.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import hugo.weaving.DebugLog;
import io.github.ovso.news.framework.utils.ObjectUtils;
import javax.inject.Inject;

public class DragDropLifecycleObserver implements LifecycleObserver {
  private RecyclerViewDragDropManager dragDropManager;

  @DebugLog @Inject public DragDropLifecycleObserver(RecyclerViewDragDropManager $dragDropManager) {
    this.dragDropManager = $dragDropManager;
  }

  @DebugLog @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) void release() {
    if (!ObjectUtils.isEmpty(dragDropManager)) {
      dragDropManager.release();
      dragDropManager = null;
    }
  }

  @DebugLog @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) void cancel() {
    if (!ObjectUtils.isEmpty(dragDropManager)) {
      dragDropManager.cancelDrag();
    }
  }
}
