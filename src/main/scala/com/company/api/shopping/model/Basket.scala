package com.company.api.shopping.model

trait Priced {
  def price: BigDecimal
  def offer: Option[Offer] = None
}

trait Basket {
  def priced: Seq[Priced]
  def totalAmount: BigDecimal = {
    priced.groupBy(_.getClass).flatMap { case (_, items) =>
      val offer = items.flatMap(_.offer).headOption.getOrElse(Offer.NoEffect)
      items.grouped(offer.groupSize).map { group =>
        if (group.size == offer.groupSize) {
          group.headOption.map(_.price * offer.priceMultiplier).getOrElse(BigDecimal(0))
        } else {
          group.map(_.price).sum
        }
      }
    }.sum
  }
}

trait Offer {
  def groupSize: Int
  def priceMultiplier: Int
}

object Offer {
  case object NoEffect extends Offer {
    val groupSize = 1
    val priceMultiplier = 1
  }
}
