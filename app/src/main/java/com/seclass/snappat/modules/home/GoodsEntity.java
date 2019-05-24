package com.seclass.snappat.modules.home;

import java.io.Serializable;

public class GoodsEntity implements Serializable {
  public String goodsUsername;
  public String goodsHint; // 货物名称
  public String goodsPrice; // 货物价格
  public String goodsTreasure;

  public GoodsEntity() {}

  public GoodsEntity(String goodsHint, String goodsPrice) {
    this.goodsHint = goodsHint;
    this.goodsPrice = goodsPrice;
  }

  public String getGoodsTreasure() {
    return goodsTreasure;
  }

  public void setGoodsTreasure(String goodsTreasure) {
    this.goodsTreasure = goodsTreasure;
  }

  public String getgoodsUsername() {
    return goodsUsername;
  }

  public void setgoodsUsername(String goodsUsername) {
    this.goodsUsername = goodsUsername;
  }

  public String getGoodsHint() {
    return goodsHint;
  }

  public void setGoodsHint(String goodsHint) {
    this.goodsHint = goodsHint;
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
        + "goodsHint='"
        + goodsHint
        + '\''
        + ", goodsPrice='"
        + goodsPrice
        + '\''
        + '}';
  }
}
