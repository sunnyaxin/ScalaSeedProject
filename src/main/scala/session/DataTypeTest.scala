package session

import scala.util.{Failure, Success, Try}

object DataTypeTest {
  def main(args: Array[String]): Unit = {
    optionTest()
    tryTest()
  }

  def optionDiv(x: Double, y: Double): Option[Double] =
    if (y == 0) None else Some(x / y)

  def optionTest(): Unit = {
    val b: Option[Int] = Some(1)
    println(b.get)
    println(b.getOrElse(0))

    println(b.map(_ + 1))
    println(b.map(x => x + 1))

    println(b.flatMap(optionDiv(_, 1)))
    println(b.flatMap(x => optionDiv(x, 1)))
    println(b.flatMap(x => optionDiv(x, 0)))

    val sum = for {
      x1 <- optionDiv(1, 2)
      x2 <- optionDiv(1, 2)
    } yield x1 + x2

    val sum2 = optionDiv(1, 2).flatMap(x1 => optionDiv(1, 2).map(x2 => x1 + x2))
    println(sum2)

//    val sum2 = optionDiv(1, 2).flatMap(
//      x1 =>
//        optionDiv(1, 2).flatMap(x2 => optionDiv(1, 2).map(x3 => x1 + x2 + x3))
//    )
//    println(sum2)
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def tryTest(): Unit = {
    val wrongInt = toInt("wrong")
    val rightInt = toInt("1")

    println(wrongInt)
    println(rightInt)

    if (rightInt.isSuccess) println(s"The answer is ${rightInt.get}")
    if (wrongInt.isFailure) println(wrongInt.failed.get.getMessage)

    wrongInt match {
      case Success(v)  => println(v)
      case Failure(ex) => println(s"Failed. Reason: ${ex.getMessage}")
    }

    println(wrongInt.toEither)
    println(wrongInt.toOption)
  }

  def arrayTest(): Unit = {
    val nums1 = Array[Int](3)
    val nums = new Array[Int](3)
    val s = Array("Hello", "World")
    nums.foreach(println)
    s.foreach(println)
    println(nums(1))
  }
}
