package session.implicitdemo.rules

object OnlyDoOnce {
  def main(args: Array[String]): Unit = {
    val me: Name = "Yaxin"
    println(me)

    import Test.int2Str
    val age: String = 18
    println(age)

//    val num: Name = 89757
  }
}

case class Name(v: String)
object Name {
  implicit def str2Name(v: String): Name = Name(v)
}
object Test {
  implicit def int2Str(v: Int): String = v.toString
}
