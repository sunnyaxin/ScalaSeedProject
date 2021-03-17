package example

object PartialFunction {
  def main(args: Array[String]): Unit = {
    println(f("hello"))
    println(f("goodbye"))
//    println(f("what"))

//    f.andThen(xx).apply("what")
    val test = f.orElse(ff).apply("what")
    println(test)

//    println(isEven(2))
//    println(isEven(1))
    println(xx(2))
  }

  val f: PartialFunction[String, Int] = {
    case "hello"   => 1
    case "goodbye" => 2
  }

  val ff: PartialFunction[String, Int] = {
    case "what"    => 999
  }

  val xx: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    def apply(x: Int) = 20 / x
    def isDefinedAt(x: Int): Boolean = x != 0
  }

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
}
