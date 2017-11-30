package com.company.api.shopping.model

import org.scalatest.{Assertion, FlatSpec, Matchers}

class BasketTest extends FlatSpec with Matchers {

  case class MyBasket(priced: Seq[MyProduct]) extends Basket
  class MyProduct(
    val price: BigDecimal,
    override val offer: Option[Offer] = None
  ) extends Priced

  case object Something extends MyProduct(price = 1)
  case object SomethingElse extends MyProduct(price = 2)
  case object AnotherThing extends MyProduct(price = 3)
  case object DiscountedThing extends MyProduct(
    price = 3,
    offer = Some(BuyOneGetOneFree)
  )

  case object BuyOneGetOneFree extends Offer {
    val amountInGroup = 2
    val priceMultiplier = 1
  }

  def basketTest(totalAmountExpected: BigDecimal)(products: MyProduct*): Assertion = {
    MyBasket(products).totalAmount shouldBe totalAmountExpected
  }

  it should "calculate the total amount" in {
    basketTest(totalAmountExpected = 6)(
      Something,
      SomethingElse,
      AnotherThing
    )
  }

  it should "calculate the total amount, inclusive of offers, bound 1" in {
    basketTest(totalAmountExpected = 9)(
      Something,
      SomethingElse,
      AnotherThing,
      DiscountedThing,
      DiscountedThing
    )
  }

  it should "calculate the total amount, inclusive of offers, bound 2" in {
    basketTest(totalAmountExpected = 12)(
      Something,
      SomethingElse,
      AnotherThing,
      DiscountedThing,
      DiscountedThing,
      DiscountedThing
    )
  }

  it should "calculate the total amount, inclusive of offers, bound 3" in {
    basketTest(totalAmountExpected = 3)(
      DiscountedThing,
      DiscountedThing
    )
  }

  it should "calculate the total amount, inclusive of offers, bound 4" in {
    basketTest(totalAmountExpected = 3)(DiscountedThing)
  }
}
