package com.adthena
package data

import scala.collection.mutable

class Basket {
  private val basket: mutable.Map[Int, Int] = mutable.Map()

  def add(id: Int): Unit = {
    if (basket.contains(id))
      basket(id) = basket(id) + 1
    else
      basket(id) = 1
  }

  def getItemsSubtotal: Double = basket.map(item =>
    Goods.getGoodByID(item._1) match {
      case Some(sellable) => sellable.price * item._2
      case None => 0
    }).sum

  def apply(id: Int): (Int, Int) = id -> basket.getOrElse(id, 0)
}