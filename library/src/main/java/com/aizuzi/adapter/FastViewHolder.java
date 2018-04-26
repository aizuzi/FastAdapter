package com.aizuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  @author liyi
 *  create at 2018/4/25
 **/
public class FastViewHolder<T> extends RecyclerView.ViewHolder{

  private FastBaseViewHolder mFastBaseViewHolder;

  public FastViewHolder(View itemView) {
    super(itemView);
  }

  public FastBaseViewHolder getFastBaseViewHolder() {
    return mFastBaseViewHolder;
  }

  public void setFastBaseViewHolder(FastBaseViewHolder mFastBaseViewHolder) {
    this.mFastBaseViewHolder = mFastBaseViewHolder;
  }
}
