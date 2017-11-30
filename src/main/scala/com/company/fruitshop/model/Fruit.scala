package com.company.fruitshop.model

import com.company.api.shopping.model.{Offer, Priced}

class Fruit(
  val price: BigDecimal,
  override val offer: Option[Offer] = None
) extends Priced

case object Apple extends Fruit(
  price = 0.6,
  offer = Some(
    Offer.TwoForOne
  )
)
case object Orange extends Fruit(
  price = 0.25,
  offer = Some(
    Offer.ThreeForThePriceOfTwo
  )
)

