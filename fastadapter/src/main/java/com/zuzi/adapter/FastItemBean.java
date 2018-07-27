package com.zuzi.adapter;

/**
 * @author liyi
 * create at 2018/4/23
 **/
public class FastItemBean<T extends FastBaseHolder> {

  private int itemType;

  private int position;

  private String itemName;

  private T clazz;

  public int getItemType() {
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


  public T getItemClass() {
    return clazz;
  }

  public void setItemClass(T itemClass) {
    this.clazz = itemClass;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
