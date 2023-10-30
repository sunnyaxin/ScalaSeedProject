package example.arena3.phase2

import scala.io.StdIn.readLine

object MainPhase2 extends App {

  def write(content: String):  Printer[Unit] =  Writer(content)
  def read:  Printer[String] =  Reader

  val program = for {
    input <- read
    _ <- write(input)
  } yield input

  program.loopWhile(_.nonEmpty).run // 进行Console读取输入、打印输出循环直到输入空值
//  program.loopWhile(_.nonEmpty).run(runFile) // 读取./input.csv中的每一行并写在./output.csv中，直到读到空行
}

sealed trait Printer[+A]
case object Reader extends  Printer[String]
case class Writer(content: String) extends  Printer[Unit]
case class Pure[A](v: A) extends  Printer[A]
case class OrderedEffect[A, B](eff:  Printer[A], f: A =>  Printer[B]) extends  Printer[B]

object Printer {
  def map[A, B](p:  Printer[A])(f: A => B):  Printer[B] = flatMap(p)((a: A) =>  Pure(f(a)))

  def flatMap[A, B](p:  Printer[A])(f: A =>  Printer[B]):  Printer[B] =  OrderedEffect(p, f)

  def run[A](p:  Printer[A]): A =
    p match {
      case  Reader          => readLine()
      case Writer(content) => println(content)
      case Pure(a)         => a
      case OrderedEffect(eff, f) =>
        eff match {
          case  Reader          => f(readLine()).run
          case Writer(content) => f(println(content)).run
          case Pure(v)         => f(v).run
          case OrderedEffect(eff1, f1) =>
            eff1.flatMap(a => f1(a).flatMap(f)).run
        }
    }

  def loopWhile[A](p:  Printer[A])(f: A => Boolean):  Printer[A] = {
    p.flatMap((a: A) => if (f(a)) loopWhile(p)(f) else  Pure(a))
  }

  implicit class PrinterOps[A, B](p:  Printer[A]) {
    def map(f: A => B):  Printer[B] =  Printer.map(p)(f)
    def flatMap(f: A =>  Printer[B]):  Printer[B] =  Printer.flatMap(p)(f)
    def run =  Printer.run(p)
    def loopWhile(f: A => Boolean):  Printer[A] =  Printer.loopWhile(p)(f)
  }
}
