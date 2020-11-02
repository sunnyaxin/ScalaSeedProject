package session.implicitdemo.extension

object GiveTypeParamsDefaultValue {
  def main(args: Array[String]): Unit = {
    println(sort(List(3, 1, 2)))
    println(sort(List(1, 2, 3))(implicitly[Ordering[Int]].reverse))
    println(sort(List(1, 2, 3))(Ordering[Int].reverse))
  }

  def sort[A](l: List[A])(implicit order: Ordering[A]) = l.sorted

  //  def sort2[A](l:List[A])(order:Ordering[A] = Ordering[A]) = l.sorted
}
