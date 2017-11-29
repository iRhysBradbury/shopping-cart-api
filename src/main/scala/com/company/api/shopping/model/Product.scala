package com.company.api.shopping.model

trait Product { self =>
  def price: BigDecimal
  def name: String = self.toString
}