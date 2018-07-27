package com.aizuzi.adapter.demo.viewholder;

import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.aizuzi.adapter.demo.R;
import com.zuzi.adapter.annotation.FastAttribute;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;

/**
 * @author liyi
 * create at 2018/4/26
 **/
@RecyclerItemLayoutId(R.layout.item_image)
public class TopViewHolder extends AppBaseHolder {

  @FastAttribute
  String url;

  @OnClick(R.id.image_view)
  public void onClickImage() {
    Toast.makeText(itemView.getContext(), "onClickImage", Toast.LENGTH_SHORT).show();
  }
}
