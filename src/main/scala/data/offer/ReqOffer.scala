package com.adthena
package data.offer

import data.Basket
import exceptions.NoSuchSellableException

class ReqOffer(offerID: Int, offerDiscount: Double, offerCount: Int, offerDiscountID: Int) extends Offer {
  val id: Int = offerID
  val discount: Double = offerDiscount
  val count: Int = offerCount
  val discountID: Int = offerDiscountID

  def applyDiscount(basket: Basket): Option[(String, Double, Int)] = {
    val basketCount = basket(id)._2
    val basketCountDiscount = basket(discountID)._2
    if (basketCount == count && basketCountDiscount != 0) {
      try
        Some(calcDiscount(discountID, basketCountDiscount))
      catch {
        case e: NoSuchSellableException =>
          println("Error: " + e.getMessage)
          None
      }
    } else
      None
  }
}

