package com.zuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  @author liyi
 *  create at 2018/4/25
 **/
public abstract class FastViewHolder<T> extends RecyclerView.ViewHolder{

  public FastViewHolder(View itemView) {
    super(itemView);
  }

  protected void onCreate(){

  }

  public <T extends View> T findViewById(int id) {
    return itemView.findViewById(id);
  }

  public abstract void refreshItem(T bean);
}
