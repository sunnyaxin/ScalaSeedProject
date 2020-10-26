package example

object ApplyTest {
  def main(args: Array[String]): Unit = {
    Test(22)
    val test = Test
    test(33)
    val test2 = Test2(1)
    test2("a")
  }
}

object Test {
  def apply(num: Int): Unit = {
    println(num)
  }
}

case class Test2(num: Int) {
  def apply(str: String): Unit = {
    println(str)
  }
}
