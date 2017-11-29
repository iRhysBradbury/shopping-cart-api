package com.company.api.shopping.model

trait Basket {
  def products: Seq[Product]
  def totalAmount: BigDecimal = products.reduce(_.price + _.price)
}
