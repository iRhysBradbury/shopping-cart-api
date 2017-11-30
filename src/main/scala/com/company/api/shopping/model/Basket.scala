package com.company.api.shopping.model

trait Priced {
  def price: BigDecimal
  def offer: Option[Offer] = None
}

trait Basket {
  def priced: Seq[Priced]
  def totalAmount: BigDecimal = {
    priced.groupBy(_.getClass).flatMap { case (_, items) =>
      val offer = items.flatMap(_.offer).headOption.getOrElse(Offer.Default)
      items.grouped(offer.amountInGroup).map { group =>
        if (group.size == offer.amountInGroup) {
          group.headOption.map(_.price * offer.priceMultiplier).getOrElse(BigDecimal(0))
        } else {
          group.map(_.price).sum
        }
      }
    }.sum
  }
}

trait Offer {
  def amountInGroup: Int
  def priceMultiplier: Int
}

object Offer {
  case object Default extends Offer {
    val amountInGroup = 1
    val priceMultiplier = 1
  }
}
