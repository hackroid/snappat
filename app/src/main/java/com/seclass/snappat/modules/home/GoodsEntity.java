package com.seclass.snappat.modules.home;

import java.io.Serializable;

public class GoodsEntity implements Serializable {
  public String goodsName; // 货物名称
  public String goodsPrice; // 货物价格

  public GoodsEntity() {}

  public GoodsEntity(String imgPath, String goodsName, String goodsPrice) {
    this.goodsName = goodsName;
    this.goodsPrice = goodsPrice;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(String goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  @Override
  public String toString() {
    return "GoodsEntity{"
        + "goodsName='"
        + goodsName
        + '\''
        + ", goodsPrice='"
        + goodsPrice
        + '\''
        + '}';
  }
}
