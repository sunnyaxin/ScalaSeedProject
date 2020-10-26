package example

import scala.collection.mutable

object ByNameTest {

  def main(args: Array[String]): Unit = {
//    testSumVarargs()
//    testRunTwice()
    testRunOnceWithSmarterCons()
//    testExistAndFold()
  }

  def sum(arg: Int*): Unit = println(arg)

  val xx: Seq[Int] => Unit = sum

  def testSumVarargs(): Unit = {
    val yy = Seq(1, 2, 3)
    val zz: Seq[Int] = Seq(1,2)
    sum(yy: _*)
    sum(zz: _*)
    xx(zz)
    sum(mutable.ArrayBuffer(1, 2): _*)
    sum(1)
    sum(1, 2)
    val x = List(1, 2, 3)
    xx(x)
    sum(x: _*)
  }

  def testRunTwice(): Unit = {
    val x = Cons(() => expensive(), () => Empty)
    val h1 = x.headOption
    val h2 = x.headOption
  }

  def testRunOnceWithSmarterCons(): Unit = {
    val xx = Stream.cons(expensive(), Empty)
//    val xx = Stream.cons(() => expensive(), Empty)
    val hh1 = xx.headOption
    val hh2 = xx.headOption
  }

  def testExistAndFold(): Unit = {
    val xx = Stream.cons(expensive(), Empty)
    val result1 = xx.exists(_ => judgeRealExpensive(xx.headOption.get))
    println("invoke exists: " + result1)

    val result2 = xx.exists2(_ => judgeRealExpensive(xx.headOption.get))
    println("invoke exists2: " + result2)
  }

  def expensive(): String = {
    println("expensive")
    "expensive"
  }

  def judgeRealExpensive(x: String): Boolean =
    if (x.contains("expensive")) true else false
}

sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty      => None
    case Cons(h, t) => Some(h())
  }
  //exists定义直观，但若stream很大且所有元素都返回false则可能导致栈溢出
  def exists(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exists(p)
  }
  //foldRight中f的第二个参数是by-name的，若不对其求值，则会提前终止遍历
  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _          => z
  }
  def exists2(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail) //调一次
//    Cons(() => { lazy val head = hd; head }, () => tail) //调两次
//    Cons(() => hd, () => tl) //调两次
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
