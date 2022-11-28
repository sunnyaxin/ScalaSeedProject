package example.basic

object ListTest {
  def main(args: Array[String]): Unit = {
    val originList = 1 :: 2 :: Nil
    val originResult = originList.fold(1)((x, y) => x + y)
    println(originResult)

    val myList = new Node[Int](1, new Node[Int](2, EndNode()))
    val myResult = myFold(1)(myList)
    println(myResult)
  }

  def myFold(initial: Int)(list: MyList[Int]): Int = {
    list match {
      case head Node tail => myFold(head + initial)(tail)
      case EndNode() => initial
    }
  }
}

trait MyList[+T]

case class Node[+T](v: T, next: MyList[T]) extends MyList[T]

case class EndNode() extends MyList[Nothing]