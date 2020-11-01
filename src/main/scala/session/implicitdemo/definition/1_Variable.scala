package session.implicitdemo.definition

object Demo1 {

  def main(args: Array[String]): Unit = {
    implicit val b: Int = 3   //1_variable

    f(1)(2)
    f(1)
//    f2(1)   //todo
  }

  def f(a: Int)(implicit b: Int): Unit = println(s"a is $a, b is $b")  //4_function_parameter
//  def f2(a: Int)(b: Int = 0): Unit = println(s"a is $a, b is $b")  //4_function_parameter
}
