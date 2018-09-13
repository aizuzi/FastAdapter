package com.aizuzi.adapter.demo.viewholder;

import com.aizuzi.adapter.demo.R;
import com.zuzi.adapter.annotation.FastAttribute;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;

/**
 * @author liyi
 * create at 2018/4/26
 **/
@RecyclerItemLayoutId(R.layout.item_text)
public abstract class ItemViewHolder extends AppBaseHolder {

  @FastAttribute(bindViewId = R.id.title_tv)
  String title;

  @FastAttribute(bindViewId = R.id.icon_view)
  int icon;

  @FastAttribute(bindViewId = R.id.image_view)
  String image;
}
