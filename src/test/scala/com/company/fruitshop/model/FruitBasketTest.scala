package com.company.fruitshop.model

import org.scalatest.{FlatSpec, Matchers}

class FruitBasketTest extends FlatSpec with Matchers {

  it should "calculate the total amount" in {
    val actual = FruitBasket(
      priced = Seq(
        Apple,
        Apple,
        Orange,
        Apple
      )
    ).totalAmount

    val expected: BigDecimal = 2.05

    actual shouldBe expected
  }
}
