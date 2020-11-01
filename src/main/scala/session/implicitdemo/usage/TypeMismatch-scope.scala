package session.implicitdemo.usage

object TypeMismatch2 {
  def main(args: Array[String]): Unit = {
    import IntConversion.int2str

    var a: String = "Hello"
    a = "See you"
    println(a)

    a = 1
    println(a)
  }
}

object IntConversion {
  implicit def int2str(v: Int): String = v.toString
}
