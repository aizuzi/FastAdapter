package com.aizuzi.adapter;

import android.view.View;

/**
 * ViewHolder基类
 *
 *  @author liyi
 *  create at 2018/4/23
 **/
public abstract class FastBaseViewHolder<T>  {

  private FastViewHolder fastViewHolder;

  public FastBaseViewHolder() {
  }

  protected void onCreate(){

  }

  public abstract void refreshItem(T bean);

  public abstract int getLayoutId();

  public View findViewById(int id) {
    return fastViewHolder.itemView.findViewById(id);
  }

  public FastViewHolder getFastViewHolder() {
    return fastViewHolder;
  }

  public void setFastViewHolder(FastViewHolder fastViewHolder) {
    this.fastViewHolder = fastViewHolder;
  }
}
