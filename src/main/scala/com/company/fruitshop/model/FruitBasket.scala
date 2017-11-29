package com.company.fruitshop.model

import com.company.api.shopping.model.Basket

case class FruitBasket(priced: Seq[Fruit]) extends Basket
