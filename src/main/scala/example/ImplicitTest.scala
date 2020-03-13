package example

object ImplicitTest {

  def main(args: Array[String]): Unit = {
    println(f(100))
  }

  def f(n: Int): Int =
    if (n == 0 || n == 1) 1
    else f(n - 1) + f(n - 2)

  def f2(n: Int): Int =
    n match {
      case 1 => 1
      case 2 => 2
      case _ => f2(n-1) + f2(n-2)
    }

  def f3(n: Int): Int =
    n match {
      case 1 => 1
      case 2 => 2
      case _ => f2(n-1) + f2(n-2)
    }
}
