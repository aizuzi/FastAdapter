package com.aizuzi.adapter.demo.viewholder;

import android.widget.ImageView;
import android.widget.TextView;
import com.aizuzi.adapter.FastBaseViewHolder;
import com.aizuzi.adapter.demo.R;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
public class ImageViewHolder extends FastBaseViewHolder<String> {

  private ImageView mImageView;

  @Override protected void onCreate() {
    super.onCreate();
    mImageView = (ImageView) findViewById(R.id.image_view);
  }

  @Override public void refreshItem(String bean) {
    //Set Image

  }

  @Override public int getLayoutId() {
    return R.layout.item_image;
  }
}
