package example

import scala.math._

object CalculatorClient extends Calculator with App {
  println(division(1, 2, 3, 4))
  println(complexCal(1, 2, 3, 4))
}

trait Calculator {

  def division(x1: Double, x2: Double, x3: Double, x4: Double): Double = {
    if (x2 == 0 || x3 == 0 || x4 == 0) {
      throw new IllegalArgumentException("The dividend cannot be zero!")
    }
    x1 / x2 / x3 / x4
  }

  def complexCal(x1: Double, x2: Double, x3: Double, x4: Double): Double = {
    if (x2 == 0) {
      throw new IllegalArgumentException("The dividend cannot be zero!")
    }
    val radicand = x1 / x2 + x3
    if (radicand <= 0) {
      throw new IllegalArgumentException("The radicand value must more than zero!")
    }
    sqrt(radicand) - x4
  }
}