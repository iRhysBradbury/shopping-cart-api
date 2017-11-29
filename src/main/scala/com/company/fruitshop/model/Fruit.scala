package com.company.fruitshop.model

import com.company.api.shopping.model.Priced

class Fruit(val price: BigDecimal) extends Priced
case object Apple extends Fruit(0.6)
case object Orange extends Fruit(0.25)
