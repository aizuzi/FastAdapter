package com.aizuzi.adapter.demo.viewholder;

import android.view.View;
import butterknife.ButterKnife;
import com.zuzi.adapter.FastViewHolder;

/**
 * @author liyi
 * create at 2018/4/28
 **/
public abstract class BaseViewHolder<T> extends FastViewHolder<T> {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  @Override protected void onCreate() {
    super.onCreate();
    ButterKnife.bind(this, itemView);
  }
}
