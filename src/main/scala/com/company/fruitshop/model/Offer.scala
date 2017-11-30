package com.company.fruitshop.model

import com.company.api.shopping.model.Offer

object Offer {
  case object TwoForOne extends Offer {
    val amountInGroup = 2
    val priceMultiplier = 1
  }
  case object ThreeForThePriceOfTwo extends Offer {
    val amountInGroup = 3
    val priceMultiplier = 2
  }
}
