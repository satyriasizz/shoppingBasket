package com.adthena
package view

import java.text.DecimalFormat

object PricePrinter {
  private val decimalFormat = new DecimalFormat()
  decimalFormat.setMaximumFractionDigits(2)
  decimalFormat.setMinimumFractionDigits(2)

  private val currencyPenny = "p"
  private val currencyMoney = "f"

  def formatPrice(price: Double): String = {
    if (price < 1)
      (price * 100).toInt + currencyPenny
    else
      currencyMoney + decimalFormat.format(price)
  }
}
