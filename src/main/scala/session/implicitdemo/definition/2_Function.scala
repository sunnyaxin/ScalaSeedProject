package session.implicitdemo.definition

object Demo2 {
  def main(args: Array[String]): Unit = {
    import HH.int2str

    var a: String = "Hello"
    a = "See you"
    println(a)

    a = 1
    println(a)
  }
}

object HH {
  implicit def int2str(v: Int): String = v.toString  //2_function
}