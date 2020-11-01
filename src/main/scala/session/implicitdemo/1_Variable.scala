package session.implicitdemo

object Demo1 {

  def main(args: Array[String]): Unit = {
    implicit val b: Int = 3   //1_variable

    f(1)(2)
    f(1)
  }

  def f(a: Int)(implicit b: Int): Unit = println(s"a is $a, b is $b")  //4_function_parameter
}
