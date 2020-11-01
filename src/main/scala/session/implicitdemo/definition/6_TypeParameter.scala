package session.implicitdemo.definition

object Demo6 {
  //def sort[A:Ordering](l:List[A])
  def main(args: Array[String]): Unit = {
    println(f1(1, 0))
  }

  def f1[A](a: A, b: A)(implicit order: Ordering[A]): Int = order.compare(a, b)

  def f2[A: Ordering](a: A, b: A): Int = implicitly[Ordering[A]].compare(a, b)

  def f3[A: Ordering](a: A, b: A): Int = {
    def inner(implicit order: Ordering[A]) = order.compare(a, b)
    inner
  }
}
