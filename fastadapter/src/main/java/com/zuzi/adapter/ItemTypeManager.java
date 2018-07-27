package com.zuzi.adapter;

import java.util.HashMap;

/**
 * @author liyi
 * create at 2018/7/26
 **/
public class ItemTypeManager {

  private Class lastItemClazz;

  private FastItemBean lastFastItemBean;

  private HashMap<Class, Integer> itemTypeMaps = new HashMap<>();

  public ItemTypeManager() {
  }

  public int getViewType(FastItemBean fastItemBean) {
    lastItemClazz = fastItemBean.getItemClass().getClass();
    lastFastItemBean = fastItemBean;
    return fastItemBean.getItemType();
  }

  public FastItemBean getItemByViewType(int viewType) {
    if (lastFastItemBean != null && lastFastItemBean.getItemType() == viewType) {
      return lastFastItemBean;
    }

    return null;
  }
}
