package session.implicitdemo.extension

object GiveCommonTypeDefaultValue {
  def main(args: Array[String]): Unit = {
    implicit val b: Int = 3
    f(1)
    f(1)(2)
    g(1)()
    g(1)(2)
  }

  def f(a: Int)(implicit b: Int): Unit = println(s"f: a is $a, b is $b")
  def g(a: Int)(b: Int = 0): Unit = println(s"g: a is $a, b is $b")

//  val gg: Int => Int => Unit = a => b => println(s"a is $a, b is $b")
}
