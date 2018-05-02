package com.aizuzi.adapter.demo.viewholder;

import android.view.View;
import com.aizuzi.adapter.demo.R;
import com.zuzi.adapter.FastViewHolder;
import com.zuzi.adapter.RecyclerItemLayoutId;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
@RecyclerItemLayoutId(R.layout.item_empty)
public class EmptyHolder extends BaseViewHolder {

  public EmptyHolder(View itemView) {
    super(itemView);
  }

  @Override protected void onCreate() {
    super.onCreate();
  }

  @Override public void refreshItem(Object bean) {

  }
}
