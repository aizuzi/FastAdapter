package com.aizuzi.adapter.demo.viewholder;

import android.widget.TextView;
import com.aizuzi.adapter.FastBaseViewHolder;
import com.aizuzi.adapter.demo.R;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
public class TextViewHolder extends FastBaseViewHolder<String> {

  private TextView mTextView;

  @Override protected void onCreate() {
    super.onCreate();
    mTextView = (TextView) findViewById(R.id.text_view);
  }

  @Override public void refreshItem(String bean) {
    mTextView.setText(bean);
  }

  @Override public int getLayoutId() {
    return R.layout.item_text;
  }
}
