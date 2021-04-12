package com.adthena
package data.offer

import data.Basket

class ItemOffer(offerID: Int, offerDiscount: Double) extends Offer {
  val id: Int = offerID
  val discount: Double = offerDiscount

  def applyDiscount(basket: Basket): Option[(String, Double, Int)] = {
    val count = basket(id)._2
    if (count != 0)
      Some(calcDiscount(id, count))
    else
      None
  }
}
