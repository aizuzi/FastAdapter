package com.zuzi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.zuzi.adapter.annotation.FastAttribute;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 快速使用多类型的RecyclerView Adapter
 *
 * @author liyi
 * create at 2018/4/23
 **/
public class FastAdapter
    extends RecyclerView.Adapter<SimpleViewHolder> {

  public List<FastItemBean> mDatas = new ArrayList<>();

  private ItemTypeManager mItemTypeManager = new ItemTypeManager();

  private LayoutInflater mLayoutInflater;

  public FastAdapter(Context context) {
    mLayoutInflater = LayoutInflater.from(context);
  }

  public <T extends FastBaseHolder> void addItem(T t) {

    FastItemBean fastItemBean = new FastItemBean();
    fastItemBean.setItemClass(t);
    fastItemBean.setItemType(t.getItemType());

    mDatas.add(fastItemBean);

    notifyDataSetChanged();
  }

  @Override public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    FastItemBean fastItemBean = mItemTypeManager.getItemByViewType(viewType);

    RecyclerItemLayoutId recyclerItemLayoutId = findItemLayout(fastItemBean.getItemClass());
    if (recyclerItemLayoutId == null) {
      throw new IllegalArgumentException("RecyclerItemLayoutId is null");
    }
    int layoutId = recyclerItemLayoutId.value();

    if (layoutId == 0) {
      throw new IllegalArgumentException("layoutId is null");
    }

    View itemView = mLayoutInflater.inflate(layoutId, parent, false);

    return new SimpleViewHolder(itemView);
  }

  @Override public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
    FastBaseHolder baseHolder = mDatas.get(position).getItemClass();
    baseHolder.itemView = holder.itemView;

    Field[] fields = baseHolder.getClass()
        .getSuperclass()
        .getDeclaredFields();

    if (fields != null) {
      for (int i = 0; i < fields.length; i++) {
        FastAttribute fastAttribute = fields[i].getAnnotation(FastAttribute.class);
        if (fastAttribute != null && fastAttribute.bindViewId() != 0) {
          View view = baseHolder.findViewById(fastAttribute.bindViewId());
          Object obj = mDatas.get(position).getItemClass().getValue(fastAttribute.bindViewId());
          if (!baseHolder.handlerViewValue(view, obj)) {
            handlerViewValue(view, obj);
          }
        }
      }
    }
    mDatas.get(position).getItemClass().bind(holder.itemView, position);
  }

  private RecyclerItemLayoutId findItemLayout(FastBaseHolder fastBaseHolder) {

    RecyclerItemLayoutId recyclerItemLayoutId =
        fastBaseHolder.getClass()
            .getSuperclass()
            .getAnnotation(RecyclerItemLayoutId.class);

    if (recyclerItemLayoutId == null) {
      recyclerItemLayoutId =
          fastBaseHolder.getClass().getAnnotation(RecyclerItemLayoutId.class);
    }
    return recyclerItemLayoutId;
  }

  private void handlerViewValue(View view, Object obj) {
    if (obj == null || view == null) return;
    if (view instanceof TextView) {
      if (obj instanceof String) {
        ((TextView) view).setText("" + obj);
      } else if (obj instanceof Integer) {
        ((TextView) view).setText((Integer) obj);
      }
    } else if (view instanceof ImageView) {
      if (obj instanceof Integer) {
        ((ImageView) view).setImageResource((Integer) obj);
      }
    }
  }

  @Override public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
  }

  @Override public void onViewDetachedFromWindow(@NonNull SimpleViewHolder holder) {
    super.onViewDetachedFromWindow(holder);
  }

  @Override
  public int getItemViewType(int position) {
    if (position < 0) return 0;
    return mItemTypeManager.getViewType(mDatas.get(position));
  }

  @Override
  public int getItemCount() {
    return mDatas.size();
  }

  public void removeItem(int position) {
    mDatas.remove(position);
    notifyItemRemoved(position);
  }

  public void clear() {
    mDatas.clear();
    notifyDataSetChanged();
  }
}
