package com.company.api.shopping.model

import org.scalatest.{FlatSpec, Matchers}

class BasketTest extends FlatSpec with Matchers {

  case class MyBasket(priced: Seq[Priced]) extends Basket
  class MyProduct(
    val price: BigDecimal,
    override val offer: Option[Offer] = None
  ) extends Priced

  case object Something extends MyProduct(price = 1)
  case object SomethingElse extends MyProduct(price =2)
  case object AnotherThing extends MyProduct(price =3)
  case object DiscountedThing extends MyProduct(price = 3, offer = Some(BuyOneGetOneFree))

  case object BuyOneGetOneFree extends Offer {
    val amountInGroup = 2
    val priceMultiplier = 1
  }

  it should "calculate the total amount" in {
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

  it should "calculate the total amount, inclusive of offers, bound 1" in {
    val actual = MyBasket(
      priced = Seq(
        Something,
        SomethingElse,
        AnotherThing,
        DiscountedThing,
        DiscountedThing
      )
    ).totalAmount

    val expected: BigDecimal = 9

    actual shouldBe expected
  }

  it should "calculate the total amount, inclusive of offers, bound 2" in {
    val actual = MyBasket(
      priced = Seq(
        Something,
        SomethingElse,
        AnotherThing,
        DiscountedThing,
        DiscountedThing,
        DiscountedThing
      )
    ).totalAmount

    val expected: BigDecimal = 12

    actual shouldBe expected
  }

  it should "calculate the total amount, inclusive of offers, bound 3" in {
    val actual = MyBasket(
      priced = Seq(
        DiscountedThing,
        DiscountedThing
      )
    ).totalAmount

    val expected: BigDecimal = 3

    actual shouldBe expected
  }

  it should "calculate the total amount, inclusive of offers, bound 4" in {
    val actual = MyBasket(
      priced = Seq(
        DiscountedThing
      )
    ).totalAmount

    val expected: BigDecimal = 3

    actual shouldBe expected
  }
}
