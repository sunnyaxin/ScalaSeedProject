package example.arena3.phase1

import scala.io.StdIn.readLine

object Main extends App {

  def write(content: String): ConsoleEffect[Unit] = Write(content)
  def read: ConsoleEffect[String] = Read

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

sealed trait ConsoleEffect[+A]
case object Read extends ConsoleEffect[String]
case class Write(content: String) extends ConsoleEffect[Unit]
case class NoEffect[A](v: A) extends ConsoleEffect[A]
case class ChainEffect[A, B](eff: ConsoleEffect[A], f: A => ConsoleEffect[B]) extends ConsoleEffect[B]

object ConsoleEffect {
  def map[A, B](p: ConsoleEffect[A])(f: A => B): ConsoleEffect[B] = flatMap(p)((a: A) => NoEffect(f(a)))

  def flatMap[A, B](p: ConsoleEffect[A])(f: A => ConsoleEffect[B]): ConsoleEffect[B] = ChainEffect(p, f)

  def run[A](p: ConsoleEffect[A]): A =
    p match {
      case Read          => readLine()
      case Write(content) => println(content)
      case NoEffect(a)         => a
      case ChainEffect(eff, f) =>
        eff match {
          case Read          => f(readLine()).run
          case Write(content) => f(println(content)).run
          case NoEffect(v)         => f(v).run
          case ChainEffect(eff1, f1) =>
            eff1.flatMap(a => f1(a).flatMap(f)).run
        }
    }

  def loopWhile[A](p: ConsoleEffect[A])(f: A => Boolean): ConsoleEffect[A] = {
//    OrderedEffect(p, (a: A) => if (f(a)) loopWhile(p)(f) else Pure(a))
//    flatMap(p)((a: A) => if (f(a)) loopWhile(p)(f) else Pure(a))
    p.flatMap((a: A) => if (f(a)) loopWhile(p)(f) else NoEffect(a))
  }

  implicit class PrinterOps[A, B](p: ConsoleEffect[A]) {
    def map(f: A => B): ConsoleEffect[B] = ConsoleEffect.map(p)(f)
    def flatMap(f: A => ConsoleEffect[B]): ConsoleEffect[B] = ConsoleEffect.flatMap(p)(f)
    def run = ConsoleEffect.run(p)
    def loopWhile(f: A => Boolean): ConsoleEffect[A] = ConsoleEffect.loopWhile(p)(f)
  }
}
