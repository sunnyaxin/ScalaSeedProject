package example.basic

object FoldLeftTest extends App {
  override def main(args: Array[String]): Unit = {
    val map1 = Map("a" -> "aa", "b" -> true, "c" -> null)
    val map2 = Map("a" -> "aaa", "b" -> false, "c" -> false, "d" -> "ddd")

    val result = map1.foldLeft(map2)((acc, ele) => { acc + ele })
    println(result)
  }
}
