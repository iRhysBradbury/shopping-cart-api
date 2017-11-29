package com.company.api.shopping.model

import org.scalatest.{FlatSpec, Matchers}

class BasketTest extends FlatSpec with Matchers {

  it should "calculate the total amount" in {
    case class MyBasket(priced: Seq[Priced]) extends Basket
    class MyProduct(val price: BigDecimal) extends Priced

    case object Something extends MyProduct(1)
    case object SomethingElse extends MyProduct(2)
    case object AnotherThing extends MyProduct(3)

    val actual = MyBasket(
      priced = Seq(
        Something,
        SomethingElse,
        AnotherThing
      )
    ).totalAmount

    val expected: BigDecimal = 6

    actual shouldBe expected
  }
}
