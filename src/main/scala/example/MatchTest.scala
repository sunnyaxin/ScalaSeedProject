package example

object MatchTest {
  def main(args: Array[String]): Unit = {
    //    testCharAndSign()
    testTypeMatch()
    //    testListMatch()
  }

  private def testCharAndSign(): Unit = {
    val ch: Char = '4'
    var sign: Int = 0

    ch match {
      case '+' => sign = 1
      case '-' => sign = -1
      case ch if Character.isDigit(ch) => sign = Character.digit(ch, 10)
    }

    println("ch is: " + ch + ", and sign is: " + sign)
  }

  private def testTypeMatch(): Unit = {
    val obj: Any = Int

    val result = obj match {
      case x: Int => x //val obj: Int = 22
      case Int => "surprise" //val obj: Any = Int
      case s: String => s
      case _: BigInt => Int.MaxValue
      case _ => 0
    }

    println(result)
  }

  private def testListMatch(): Unit = {
    val lst = 0 :: 2 :: 2 :: Nil

    val result = lst match {
      case x :: y :: Nil => s"$x $y"
      case 0 :: tail => s"0 ... $tail"
      case _ => "something else"
    }

    println(result)
  }
}

