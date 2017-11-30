package com.company.api.shopping.model

trait Priced {
  /**
    * @return the price
    */
  def price: BigDecimal

  /**
    * @return an Offer if there is one
    */
  def offer: Option[Offer] = None
}

trait Basket {
  /**
    * @return the Priced items in this Basket
    */
  def priced: Seq[Priced]

  /**
    * @return the total amount in the basket, taking into consideration any offers applicable
    */
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
  /**
    * @return the size which the offer extends to, eg. 9 for the price of 4; 9 would be the groupSize
    */
  def groupSize: Int

  /**
    * @return the amount we should multiply one to a price for the price of that group
    */
  def priceMultiplier: Int
}

object Offer {
  case object NoEffect extends Offer {
    val groupSize = 1
    val priceMultiplier = 1
  }
}
