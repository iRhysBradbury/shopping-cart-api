package com.company.fruitshop.model

import org.scalatest.{FlatSpec, Matchers}

class FruitBasketTest extends FlatSpec with Matchers {

  it should "calculate the total amount, inclusive of offers of Fruit" in {
    val actual = FruitBasket(
      priced = Seq(
        Apple,
        Apple,
        Orange,
        Apple
      )
    ).totalAmount

    val expected: BigDecimal = 1.45

    actual shouldBe expected
  }
}
