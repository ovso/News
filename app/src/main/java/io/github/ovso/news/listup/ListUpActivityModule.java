package io.github.ovso.news.listup;

import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import io.github.ovso.news.framework.resources.ResourcesProvider;
import io.github.ovso.news.framework.resources.ResourcesProviderImpl;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.news.R;
import io.github.ovso.news.db.AppDatabase;
import io.github.ovso.news.db.WebsiteEntity;
import io.github.ovso.news.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.news.framework.adapter.BaseAdapterView;
import io.github.ovso.news.framework.rx.SchedulersFacade;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.listup.listener.OnAdapterListener;
import timber.log.Timber;

@Module
public class ListUpActivityModule {
    @Singleton
    @Provides
    public ListUpPresenter provideListUpPresenter(ListUpPresenter.View view, AppDatabase
            database, BaseAdapterDataModel<WebsiteEntity>
                                                          adapterDataModel, SchedulersFacade schedulers, ResourcesProviderImpl resourcesProvider) {
        return new ListUpPresenterImpl(view, database, adapterDataModel, schedulers, resourcesProvider);
    }

    @Singleton
    @Provides
    public ListUpAdapter provideListUpAdapter(OnAdapterListener<WebsiteEntity> onAdapterListener) {
        return new ListUpAdapter.Builder().setOnAdapterListener(onAdapterListener)
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
        manager.setDraggingItemAlpha(.5f);
        return manager;
    }

    @Singleton
    @Provides
    public RecyclerView.Adapter provideWrapperAdapter(RecyclerViewDragDropManager dragDropManager, ListUpAdapter adapter) {
        return dragDropManager.createWrappedAdapter(adapter);
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

    @Singleton
    @Provides
    public LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
