package example.basic

object ForTest {
  def main(args: Array[String]): Unit = {

    //TARGET: list(1,2,3) => list(2,3,4)
    val list: List[Int] = 1 :: 2 :: 3 :: Nil
    println(originForLoop(list))
    println(richForLoop(list))
    println(mapReplaceFor(list))

    //TARGET: list(1,2,3) => list(2,3,4,3,4,5,4,5,6)
    println(richForLoop2(list))
    println(mapReplaceFor2(list))
  }

  def originForLoop(origin: List[Int]): Unit =
    for (i <- origin.indices) {
      origin(i) + 1
    }

  def richForLoop(origin: List[Int]): List[Int] =
    for { x <- origin } yield x + 1

  def mapReplaceFor(origin: List[Int]): List[Int] =
    origin.map(x => x + 1)

  def richForLoop2(origin: List[Int]): List[Int] =
    for {
      x <- origin
      y <- origin
    } yield x + y

  def mapReplaceFor2(origin: List[Int]): List[Int] =
    origin.flatMap { x =>
      origin.map(y => x + y)
    }
}
