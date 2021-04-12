package com.adthena

import data.Basket
import data.offer.{ItemOffer, ReqOffer}
import view.Menu


object Main extends App {

  val offersList = List(new ItemOffer(4, 0.9), new ReqOffer(3, 0.5, 2, 2))
  val basket = new Basket
  val menu = new Menu(basket, offersList)
  menu.printMainMenu()
  menu.runSale()
}
