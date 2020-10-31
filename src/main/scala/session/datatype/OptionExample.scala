package session.datatype

object OptionExample {
  def main(args: Array[String]): Unit = {
    //origin
    //    println(div(8, 2))
    //    println(div(1, 0))

    testRichResult()
    testMap()
    testFlatMap()
  }

  private def testRichResult(): Unit = {
    println(richDiv(8, 2))
    println(richDiv(1, 0))

    val richResult: RichResult = Result(1)
    println(s"RichResult value is: ${richResult.get}")
  }

  private def testMap(): Unit = {
    //map: a + 1  // f:Int => Int
    val a: RichResult = Result(1)
    val b: RichResult = a match {
      case NullResult => NullResult
      case Result(v) => Result(v + 1)
    }
    val b2: RichResult = a.map(x => x + 1)
    println(s"-----map-----b: $b, b2: $b2")
  }

  private def testFlatMap(): Unit = {
    //flatmap: richDiv(4, anotherRichResult)  // f:Int => RichResult
    val a: RichResult = Result(1)
    val b: RichResult = a match {
      case NullResult => NullResult
      case Result(v) => richDiv(4, v)
    }
    val b2 = a.flatMap(x => richDiv(4, x))
    println(s"---flatmap---b: $b, b2: $b2")
  }

  def div(x: Int, y: Int): Int = x / y

  sealed trait RichResult {
    def get: Int

    def flatMap(f: Int => RichResult): RichResult = this match {
      case NullResult => NullResult
      case Result(v) => f(v)
    }

    def map(f: Int => Int): RichResult = this match {
      case NullResult => NullResult
      case Result(v) => Result(f(v))
    }
  }

  case object NullResult extends RichResult {
    override def get: Nothing = throw new NoSuchElementException("None.get")
  }

  case class Result(v: Int) extends RichResult {
    override def get: Int = v
  }

  def richDiv(x: Int, y: Int): RichResult = y match {
    case 0 => NullResult
    case _ => Result(x / y)
  }
}
