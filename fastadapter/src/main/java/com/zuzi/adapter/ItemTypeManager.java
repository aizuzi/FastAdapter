package com.zuzi.adapter;

import java.util.HashMap;

/**
 * @author liyi
 * create at 2018/7/26
 **/
public class ItemTypeManager {

  private Class lastItemClazz;

  private FastBaseHolder lastFastItemBean;

  private HashMap<Class, Integer> itemTypeMaps = new HashMap<>();

  public ItemTypeManager() {
  }

  public int getViewType(FastBaseHolder fastItemBean) {
    lastItemClazz = fastItemBean.getClass();
    lastFastItemBean = fastItemBean;
    return fastItemBean.getItemType();
  }

  public FastBaseHolder getItemByViewType(int viewType) {
    if (lastFastItemBean != null && lastFastItemBean.getItemType() == viewType) {
      return lastFastItemBean;
    }

    return null;
  }
}
