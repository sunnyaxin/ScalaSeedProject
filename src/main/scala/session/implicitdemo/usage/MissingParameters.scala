package session.implicitdemo.usage

object MissingParameters {
  def main(args: Array[String]): Unit = {
    implicit val b: Int = 0

    f(1)(2)
    f(1)
  }

  def f(a: Int)(implicit b: Int): Unit = println(s"a is $a, b is $b")
}
