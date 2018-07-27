package com.aizuzi.adapter.demo.viewholder;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.zuzi.adapter.FastBaseHolder;

/**
 * @author liyi
 * create at 2018/4/28
 **/
public abstract class AppBaseHolder extends FastBaseHolder {
  @Override public void bind(View itemView, int position) {
    super.bind(itemView, position);
    ButterKnife.bind(this, itemView);
  }

  @Override public boolean handlerViewValue(View view, Object obj) {

    if (view instanceof ImageView && obj != null) {
      if (obj instanceof String) {
        Glide.with(itemView.getContext())
            .load("" + obj)
            .into((ImageView) view);
      }
    }
    return super.handlerViewValue(view, obj);
  }
}
