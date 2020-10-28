package session

import scala.util.{Failure, Success, Try}

object DataTypeTest {
  def main(args: Array[String]): Unit = {
    //    optionTest()
    eitherTest()
    //    listTest()
    //    tupleTest()
    //    mapTest()
    //    tryTest()
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
      x3 <- optionDiv(2, 3)
    } yield x1 + x2 + x3

    //    val sum2 = optionDiv(1, 2).flatMap(x1 => optionDiv(1, 2).map(x2 => x1 + x2))
    //    println(sum2)

    val sum2 = optionDiv(1, 2)
      .flatMap(x1 =>
        optionDiv(1, 2)
          .flatMap(x2 =>
            optionDiv(1, 2)
              .map(x3 => x1 + x2 + x3))
      )
    println(sum2)
  }

  def eitherDiv(x: Double, y: Double): Either[String, Double] =
    if (y == 0) Left("y is 0") else Right(x / y)

  def eitherTest(): Unit = {
    val a: Either[String, Int] = Left("Some error happen")
    val b: Either[String, Int] = Right(1)
    b.map(_ + 1) // Right(2)
    b.getOrElse(0)
    b.flatMap(x => eitherDiv(x, 1)) // Right(1)
    b.flatMap(x => eitherDiv(x, 0)) // Left("y is 0")
    println(a)
  }

  def listTest(): Unit = {
    val a = List(1, 2, 3)
    val b = 1 :: List(4, 2) //The :: operator makes a new list from a given head and tail
    val b2 = 1 :: 4 :: 2 :: Nil
    val c = List(1) ::: List(1, 2, 3)
    val d = List(1) :: List(1, 2, 3) //List(List(1), 1, 2, 3)
    //(:: prepends a single item whereas ::: prepends a complete list)

    List(1, 2, 3).map(_ + 1) //List(2, 3, 4)
    List(1, 2, 3).flatMap(x => List(x, x)) //List(1, 1, 2, 2, 3, 3)
    List(3, 2, 1).sorted //List(1, 2, 3)
    List(3, 2, 1).filter(_ % 2 == 1) //List(3, 1)
    List(3, 2, 1).max //3
    List(3, 2, 1).min //1
    List(3, 2, 1).sum //6
    List(3, 2, 1).fold(0)((x, y) => x + y) //6
  }

  def tupleTest(): Unit = {
    val tuple: (Int, Double, String) = (1, 3.14, "Fred") //(Int, Double, String)
    val secondItem = tuple._2
    val (first, second, _) = tuple //use pattern matching to get at the components of a tuple
    println(s"first: $first, second: $second, second item: $secondItem")
    println("New York".partition(_.isUpper)) //("NY","ew ork"))
  }

  def mapTest(): Unit = {
    val immutableScores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
    val mutableScores =
      scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
    val scores = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))
    println(
      s"immutableScores: $immutableScores, mutableScores: $mutableScores, scores: $scores"
    )

    val bobScore = scores("Bob")
    val bobScore2 = scores.getOrElse("Bob", 0)
    println(s"bobScore: $bobScore, bobScore2: $bobScore2")

    mutableScores("Bob") = 10
    mutableScores("Fred") = 7
    mutableScores ++= Map("Bob" -> 10, "Fred" -> 7)
    mutableScores -= "Alice"
    println(s"updated mutableScores: $mutableScores")

    val newScores = immutableScores + ("Bob" -> 10, "Fred" -> 7)
    println(s"updated immutableScores: $immutableScores")
    println(s"newScores: $newScores")

    for {
      (name, score) <- scores
    } yield (name, score)
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def toInt2(s: String): Option[Int] =
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }

  def tryTest(): Unit = {
    val wrongInt = toInt("wrong")
    val rightInt = toInt("1")

    println(wrongInt)
    println(rightInt)

    if (rightInt.isSuccess) println(s"The answer is ${rightInt.get}")
    if (wrongInt.isFailure) println(wrongInt.failed.get.getMessage)

    wrongInt match {
      case Success(v) => println(v)
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
