package io.github.ovso.news.listup;

import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.content.ContextCompat;

import com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.adapter.OnRecyclerItemClickListener;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.listup.listener.OnMoveItemListener;

import javax.inject.Singleton;

@Module
public class ListUpActivityModule {
    @Singleton
    @Provides
    public ListUpPresenter provideListUpPresenter(ListUpPresenter.View view, AppDatabase
            database, BaseAdapterDataModel<WebsiteEntity>
                                                          adapterDataModel, SchedulersFacade schedulers) {
        return new ListUpPresenterImpl(view, database, adapterDataModel, schedulers);
    }

    @Singleton
    @Provides
    public ListUpAdapter provideListUpAdapter(
            OnRecyclerItemClickListener<WebsiteEntity> itemClickListener, OnMoveItemListener moveItemListener) {
        return new ListUpAdapter.Builder().setOnItemClickListener(itemClickListener).setOnMoveItemListener(moveItemListener)
                .build();
    }

    @Provides
    public BaseAdapterView provideAdapterView(ListUpAdapter adapter) {
        return adapter;
    }

    @Provides
    public BaseAdapterDataModel<WebsiteEntity> provideAdapterDataModel(ListUpAdapter adapter) {
        return adapter;
    }

    @Singleton
    @Provides
    public RecyclerViewDragDropManager provideRecyclerViewDragDropManager() {
        RecyclerViewDragDropManager manager = new RecyclerViewDragDropManager();
        return manager;
    }

    @Singleton
    @Provides
    public DraggableItemAnimator provideDraggableItemAnimator() {
        return new DraggableItemAnimator();
    }

    @Singleton
    @Provides
    public ItemShadowDecorator provideItemShadowDecorator(Context context) {
        return new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.material_shadow_z1));
    }

    @Singleton
    @Provides
    public SimpleListDividerDecorator provideSimpleListDividerDecorator(Context context) {
        return new SimpleListDividerDecorator(ContextCompat.getDrawable(context, R.drawable.list_divider_h), true);
    }
}
