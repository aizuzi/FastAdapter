package com.zuzi.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * @author liyi
 * create at 2018/7/25
 **/
public abstract class FastBaseHolder {

  public View itemView;

  private SparseArray<Object> attributeValues = new SparseArray<>();

  private int itemType;

  private int position;

  public int getItemType() {
    return itemType;
  }

  int getPosition() {
    return position;
  }

  public void onCreate(View itemView){}

  public void bind(View itemView, int position) {
    this.itemView = itemView;
    this.position = position;
  }

  public void unBind(int position) {
  }

  public <T> View findViewById(int viewId) {
    return itemView.findViewById(viewId);
  }

  public Object getValue(int key) {
    return attributeValues.get(key);
  }

  public boolean handlerViewValue(View view, Object obj) {
    return false;
  }

  protected void putValue(int key, Object obj) {
    attributeValues.put(key, obj);
  }
}
