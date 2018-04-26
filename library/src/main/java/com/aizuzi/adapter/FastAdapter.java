package com.aizuzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

  private HashMap<Class<? extends FastBaseViewHolder>,Integer> itemMaps = new HashMap<>();

  public FastAdapter(Context context) {
    mLayoutInflater = LayoutInflater.from(context);
  }

  public <TD extends FastBaseViewHolder<T>,T> void addItem(Class<TD> clazz) {
    addItem(clazz,null);
  }

  public <TD extends FastBaseViewHolder<T>,T> void addItem(Class<TD> clazz,T t) {

    Integer itemType = itemMaps.get(clazz);

    if(itemType == null || itemType <=0){
      itemType = obtainItemType();
      itemMaps.put(clazz,itemType);
    }

    FastItemBean fastItemBean = new FastItemBean();
    fastItemBean.setItemClass(clazz);
    fastItemBean.setItemType(itemType);
    fastItemBean.setData(t);
    mDatas.add(fastItemBean);

    notifyDataSetChanged();
  }

  private int obtainItemType(){
    int type = new Random().nextInt(999999);
    if(itemMaps.containsValue(type)){
      return obtainItemType();
    }
    return type;
  }

  private Class<? extends FastBaseViewHolder> getClassByType(int type){
    //if(itemMaps.containsValue(type))return null;
    Iterator<Map.Entry<Class<? extends FastBaseViewHolder>,Integer>> iterator = itemMaps.entrySet().iterator();
    while (iterator.hasNext()){
      Map.Entry<Class<? extends FastBaseViewHolder>,Integer> item = iterator.next();
      if(item.getValue() == type){
        return item.getKey();
      }
    }
    return null;
  }

  @Override public FastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    try {

      FastBaseViewHolder fastBaseViewHolder = getClassByType(viewType).newInstance();
      View itemView = mLayoutInflater.inflate(fastBaseViewHolder.getLayoutId(), null);
      //FastViewHolder fastViewHolder = (FastViewHolder) ConstructorUtils.invokeConstructor(fastBaseViewHolder.get(), itemView);
      FastViewHolder fastViewHolder = new FastViewHolder(itemView);

      fastBaseViewHolder.setFastViewHolder(fastViewHolder);
      fastViewHolder.setFastBaseViewHolder(fastBaseViewHolder);

      fastBaseViewHolder.onCreate();

      return fastViewHolder;
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void onBindViewHolder(FastViewHolder holder, int position) {
    holder.getFastBaseViewHolder().refreshItem(mDatas.get(position).getData());
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
