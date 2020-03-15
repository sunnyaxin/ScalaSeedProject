package example

object ByNameTest {
  def main(args: Array[String]): Unit = {
//    testSumVarargs()

    //运行两次expensive
    val x = Cons(() => expensive(), () => Empty)
    val h1 = x.headOption
    val h2 = x.headOption
    //只在第一次使用的时候被强制求值
    val xx = Stream.cons(expensive(), Empty)
    val hh1 = xx.headOption
    val hh2 = xx.headOption
  }

  def sum(arg: Int*): Unit = println(arg)

  def testSumVarargs(): Unit = {
    sum(1)
    sum(1, 2)
    val x = List(1, 2, 3)
    sum(x: _*)
  }

  def expensive(): Unit = println("expensive")
}

sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty      => None
    case Cons(h, t) => Some(h())
  }
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
