package com.zuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author liyi
 * create at 2018/7/25
 **/
public class SimpleViewHolder extends RecyclerView.ViewHolder {

  public SimpleViewHolder(View itemView) {
    super(itemView);
  }

  public <T extends View> T findViewById(int id) {
    return itemView.findViewById(id);
  }
}
