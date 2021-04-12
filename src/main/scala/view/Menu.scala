package com.adthena
package view

import data.offer.Offer
import data.{Basket, Goods, Sellable}

import scala.io.StdIn.readLine

class Menu(aBasket: Basket, offersList: List[Offer]) {

  val basket: Basket = aBasket
  val offers: List[Offer] = offersList
  val maxID: Int = Goods.getMaxID
  val exitID: Int = maxID + 1
  val wrongMenuMsg = "Please enter one of menu numbers"

  def printMainMenu(): Unit = {
    println("Add to shopping basket: ")
    println("0 - Calculate")
    println(Goods.goods.map(i =>
      i._1 + " - " + formatSellable(i._2)).mkString("\n"))
    println(s"$exitID - Finish shopping (Exit)")
  }

  def runSale(): Unit = {
    var id = 1
    while (id != exitID) {
      try {
        id = readLine().toInt
      } catch {
        case e: Exception =>
          println(wrongMenuMsg)
      }
      if (id == 0) {
        val itemsSubtotal = basket.getItemsSubtotal
        println("Subtotal: " + PricePrinter.formatPrice(itemsSubtotal))

        val totalDiscount = offersList.map { offer =>
          val mbDiscount = offer.applyDiscount(basket)
          mbDiscount match {
            case Some(discount) =>
              val name = discount._1
              val priceDiscount = discount._2
              val discountPercent = discount._3

              println(s"$name $discountPercent% off: -${PricePrinter.formatPrice(priceDiscount)}")

              priceDiscount
            case None => 0
          }
        }.sum

        if(totalDiscount == 0)
          println("(No offers available)")

        val totalPrice = itemsSubtotal - totalDiscount
        println("Total price: " + PricePrinter.formatPrice(totalPrice))
      } else if (id <= maxID)
        basket.add(id)
      else
        println(wrongMenuMsg)
    }
  }

  private def formatSellable(item: Sellable): String =
    s"${item.name} - ${PricePrinter.formatPrice(item.price)} per ${item.units}"
}
