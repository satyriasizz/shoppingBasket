package com.adthena
package data.offer

import data.{Basket, Goods}
import exceptions.NoSuchSellableException

trait Offer {
  val id: Int
  val discount: Double

  def applyDiscount(basket: Basket): Option[(String, Double, Int)]

  protected def calcDiscount(id: Int, count: Int): (String, Double, Int) = {
    Goods.getGoodByID(id) match {
      case Some(item) =>
        val itemDiscount = (item.price - item.price * discount) * count
        val discountRounded = Math.round(itemDiscount * 100) / 100d
        (item.name, discountRounded, Math.round((1 - discount) * 100).toInt)
      case None => throw new NoSuchSellableException(s"No sellable for $id exception")
    }
  }
}
