package com.zuzi.adapter;

/**
 * @author liyi
 * create at 2018/4/23
 **/
public class FastItemBean<T> implements MultiItemEntity {

  private int itemType;

  private String itemName;

  private Class<? extends FastViewHolder> itemClass;

  private T data;

  @Override public int getItemType() {
    return itemType;
  }

  public void setItemType(int itemType) {
    this.itemType = itemType;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Class<? extends FastViewHolder> getItemClass() {
    return itemClass;
  }

  public void setItemClass(
      Class<? extends FastViewHolder> itemClass) {
    this.itemClass = itemClass;
  }
}
