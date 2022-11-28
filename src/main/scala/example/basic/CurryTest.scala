package example.basic

object CurryTest {

  def main(args: Array[String]): Unit = {
    val add = (x: Int, y: Int) => x + y
    println(add(1, 2))

    add.curried
  }
}
