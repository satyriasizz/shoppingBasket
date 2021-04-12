package com.adthena
package data.offer

import data.{Basket, Goods}

trait Offer {
  val id: Int
  val discount: Double

  def applyDiscount(basket: Basket): Option[(String, Double, Int)]

  protected def calcDiscount(id: Int, count: Int): (String, Double, Int) = {
    val item = Goods.getGoodByID(id) //todo this may be not safe
    val itemDiscount = (item.price - item.price * discount) * count
    val discountRounded = Math.round(itemDiscount * 100) / 100d

    (item.name, discountRounded, Math.round((1 - discount) * 100).toInt)
  }
}
