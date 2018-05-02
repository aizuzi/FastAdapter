package com.aizuzi.adapter.demo.viewholder;

import android.view.View;
import android.widget.ImageView;
import com.aizuzi.adapter.demo.R;
import com.zuzi.adapter.FastViewHolder;
import com.zuzi.adapter.RecyclerItemLayoutId;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
@RecyclerItemLayoutId(R.layout.item_image)
public class ImageViewHolder extends BaseViewHolder<String> {

  private ImageView mImageView;

  public ImageViewHolder(View itemView) {
    super(itemView);
  }

  @Override protected void onCreate() {
    super.onCreate();
    mImageView = (ImageView) findViewById(R.id.image_view);
  }

  @Override public void refreshItem(String bean) {
    //Set Image

  }

}
