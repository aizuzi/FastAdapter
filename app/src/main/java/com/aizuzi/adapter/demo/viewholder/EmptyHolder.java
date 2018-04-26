package com.aizuzi.adapter.demo.viewholder;

import com.aizuzi.adapter.FastBaseViewHolder;
import com.aizuzi.adapter.demo.R;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
public class EmptyHolder extends FastBaseViewHolder {


  @Override protected void onCreate() {
    super.onCreate();
  }

  @Override public void refreshItem(Object bean) {

  }

  @Override public int getLayoutId() {
    return R.layout.item_empty;
  }
}
