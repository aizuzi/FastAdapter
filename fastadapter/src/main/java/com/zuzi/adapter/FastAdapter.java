package com.zuzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 快速使用多类型的RecyclerView Adapter
 *
 * @author liyi
 * create at 2018/4/23
 **/
public class FastAdapter
    extends RecyclerView.Adapter<FastViewHolder> {

  public List<FastItemBean> mDatas = new ArrayList<>();

  private LayoutInflater mLayoutInflater;

  private HashMap<Class<? extends FastViewHolder>, Integer> itemMaps = new HashMap<>();

  public FastAdapter(Context context) {
    mLayoutInflater = LayoutInflater.from(context);
  }

  public <TD extends FastViewHolder<T>, T> void addItem(Class<TD> clazz) {
    addItem(clazz, null);
  }

  public <TD extends FastViewHolder<T>, T> void addItem(Class<TD> clazz, T t) {

    Integer itemType = itemMaps.get(clazz);

    if (itemType == null || itemType <= 0) {
      itemType = obtainItemType();
      itemMaps.put(clazz, itemType);
    }

    FastItemBean fastItemBean = new FastItemBean();
    fastItemBean.setItemClass(clazz);
    fastItemBean.setItemType(itemType);
    fastItemBean.setData(t);
    mDatas.add(fastItemBean);

    notifyDataSetChanged();
  }

  private int obtainItemType() {
    int type = new Random().nextInt(999999);
    if (itemMaps.containsValue(type)) {
      return obtainItemType();
    }
    return type;
  }

  private Class<? extends FastViewHolder> getClassByType(int type) {
    //if(itemMaps.containsValue(type))return null;
    Iterator<Map.Entry<Class<? extends FastViewHolder>, Integer>> iterator =
        itemMaps.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Class<? extends FastViewHolder>, Integer> item = iterator.next();
      if (item.getValue() == type) {
        return item.getKey();
      }
    }
    return null;
  }

  @Override public FastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    try {

      Class<? extends FastViewHolder> fastBaseViewHolderClass = getClassByType(viewType);

      RecyclerItemLayoutId recyclerItemLayoutId =
          fastBaseViewHolderClass.getAnnotation(RecyclerItemLayoutId.class);
      if (recyclerItemLayoutId == null) {
        throw new IllegalArgumentException("RecyclerItemLayoutId is null");
      }
      int layoutId = recyclerItemLayoutId.value();

      if (layoutId == 0) {
        throw new IllegalArgumentException("layoutId is null");
      }

      View itemView = mLayoutInflater.inflate(layoutId, parent, false);

      try {
        FastViewHolder fastViewHolder =
            fastBaseViewHolderClass.getConstructor(View.class).newInstance(itemView);
        fastViewHolder.onCreate();
        return fastViewHolder;
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
      return null;
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void onBindViewHolder(FastViewHolder holder, int position) {
    holder.refreshItem(mDatas.get(position).getData());
  }

  @Override
  public int getItemViewType(int position) {
    if (position < 0) return 0;
    return mDatas.get(position).getItemType();
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
