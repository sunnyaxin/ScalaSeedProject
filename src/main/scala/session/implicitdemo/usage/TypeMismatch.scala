package session.implicitdemo.usage

object TypeMismatch {
  def main(args: Array[String]): Unit = {
    implicit def int2str(v: Int): String = v.toString

    var a: String = "Hello"
    a = "See you"
    println(a)

    a = 1
    println(a)
  }
}
