package session.implicitdemo

object Demo2 {
  implicit def int2str(v: Int): String = v.toString  //2_function

  def main(args: Array[String]): Unit = {
    var a: String = "Hello"
    a = "See you"
    println(a)

    a = 1
    println(a)
  }
}