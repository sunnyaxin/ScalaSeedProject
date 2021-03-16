package example

object PartialFunction {
  def main(args: Array[String]): Unit = {
    println(f("hello"))
    println(f("goodbye"))
//    println(f("what"))
    println(isEven(2))
//    println(isEven(1))
  }

  val f: PartialFunction[String, Int] = {
    case "hello"   => 1
    case "goodbye" => 2
  }

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
}
