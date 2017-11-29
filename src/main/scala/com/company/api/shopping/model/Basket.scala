package com.company.api.shopping.model

trait Priced {
  def price: BigDecimal
}

trait Basket {
  def priced: Seq[Priced]
  def totalAmount: BigDecimal = priced.map(_.price).sum
}
