package session.implicitdemo.extension

object Demo2 {
  def main(args: Array[String]): Unit = {
    println(compare(1, 2))
//    println(compare(Age(1), Age(3)))
  }

  def sort[A: Ordering](l: List[A]) = l.sorted

  def sort2[A](l: List[A])(implicit order: Ordering[A]) = l.sorted

  def compare[A: Ordering](x: A, y: A): Int = implicitly[Ordering[A]].compare(x, y)

  def compare2[A](x: A, y: A)(implicit order: Ordering[A]): Int =
    order.compare(x, y)
}

case class Age(v: Int)
