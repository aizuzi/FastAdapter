package com.aizuzi.adapter.demo.viewholder;

import android.view.View;
import android.widget.TextView;
import com.aizuzi.adapter.demo.R;
import com.zuzi.adapter.FastViewHolder;
import com.zuzi.adapter.RecyclerItemLayoutId;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
@RecyclerItemLayoutId(R.layout.item_text)
public class TextViewHolder extends BaseViewHolder<String> {

  private TextView mTextView;

  public TextViewHolder(View itemView) {
    super(itemView);
  }

  @Override protected void onCreate() {
    super.onCreate();
    mTextView = findViewById(R.id.text_view);
  }

  @Override public void refreshItem(String bean) {
    mTextView.setText(bean);
  }

}
