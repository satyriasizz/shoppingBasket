package com.adthena
package data

object Goods {

  val goods: Map[Int, Sellable] = List(
    Sellable("Soup", "tin", 0.65),
    Sellable("Bread", "loaf", 0.8),
    Sellable("Milk", "bottle", 1.3),
    Sellable("Apples", "bag", 1))
    .zipWithIndex
    .map { case (v, i) => i + 1 -> v }
    .toMap

  def getMaxID: Int = goods.keys.max

  def getGoodByID(id: Int): Option[Sellable] = goods.get(id)
}