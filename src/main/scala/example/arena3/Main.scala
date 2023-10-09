package example.arena3

import scala.io.StdIn.readLine

object Main extends App {

  def write(content: String): Printer[Unit] = Writer(content)
  def read: Printer[String] = Reader

  val program = for {
    _ <- write("Input:")
    input <- read
    _ <- write("Output:")
    _ <- write(input)
  } yield input
  // 此行以上没有任何Effect

  program.run
  // Console输出: Input:
  // Console等待输入
  // Console输出: Output:
  // Console输出: 上一行输入结果

  println("=============")
  program.loopWhile(_.nonEmpty).run
//  write("aaa").loopWhile(_ => true).run
  // 进行上述行为的循环直到输入空值
}

sealed trait Printer[+A]
case object Reader extends Printer[String]
case class Writer(content: String) extends Printer[Unit]
case class Pure[A](v: A) extends Printer[A]
case class OrderedEffect[A, B](eff: Printer[A], f: A => Printer[B]) extends Printer[B]

object Printer {
  def map[A, B](p: Printer[A])(f: A => B): Printer[B] = flatMap(p)((a: A) => Pure(f(a)))

  def flatMap[A, B](p: Printer[A])(f: A => Printer[B]): Printer[B] = OrderedEffect(p, f)

  def run[A](p: Printer[A]): A =
    p match {
      case Reader          => readLine()
      case Writer(content) => println(content)
      case Pure(a)         => a
      case OrderedEffect(eff, f) =>
        eff match {
          case Reader          => f(readLine()).run
          case Writer(content) => f(println(content)).run
          case Pure(v)         => f(v).run
          case OrderedEffect(eff1, f1) =>
            eff1.flatMap(a => f1(a).flatMap(f)).run
        }
    }

  def loopWhile[A](p: Printer[A])(f: A => Boolean): Printer[A] = {
//    OrderedEffect(p, (a: A) => if (f(a)) loopWhile(p)(f) else Pure(a))
//    flatMap(p)((a: A) => if (f(a)) loopWhile(p)(f) else Pure(a))
    p.flatMap((a: A) => if (f(a)) loopWhile(p)(f) else Pure(a))
  }

  implicit class PrinterOps[A, B](p: Printer[A]) {
    def map(f: A => B): Printer[B] = Printer.map(p)(f)
    def flatMap(f: A => Printer[B]): Printer[B] = Printer.flatMap(p)(f)
    def run = Printer.run(p)
    def loopWhile(f: A => Boolean): Printer[A] = Printer.loopWhile(p)(f)
  }
}
