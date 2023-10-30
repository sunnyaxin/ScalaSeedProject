package example.arena3.phase2

import java.io.FileOutputStream
import scala.annotation.tailrec
import scala.io.Source
import scala.io.StdIn.readLine

object MainPhase2 extends App {

  def write(content: String): ConsoleEffect[Unit] = Write(content)
  def read: ConsoleEffect[String] = Read
  implicit val runFile: Boolean = true

  val program = for {
    input <- read
    _ <- write(input)
  } yield input

//  program.loopWhile(_.nonEmpty).run // 进行Console读取输入、打印输出循环直到输入空值
  program
    .loopWhile(_.nonEmpty)
    .run(runFile) // 读取./input.csv中的每一行并写在./output.csv中，直到读到空行
}

sealed trait ConsoleEffect[+A]
case object Read extends ConsoleEffect[String]
case class Write(content: String) extends ConsoleEffect[Unit]
case class NoEffect[A](v: A) extends ConsoleEffect[A]
case class ChainEffect[A, B](eff: ConsoleEffect[A], f: A => ConsoleEffect[B])
    extends ConsoleEffect[B]

object ConsoleEffect {
  def map[A, B](p: ConsoleEffect[A])(f: A => B): ConsoleEffect[B] =
    flatMap(p)((a: A) => NoEffect(f(a)))

  def flatMap[A, B](p: ConsoleEffect[A])(f: A => ConsoleEffect[B]): ConsoleEffect[B] =
    ChainEffect(p, f)

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

  val input = Source.fromFile("input.csv")
  val output = new FileOutputStream("output.csv")

  @tailrec
  def runFile[A](p: ConsoleEffect[A]): A = {
    p match {
      case Read          => input.getLines().mkString(",")
      case Write(content) => output.write(content.getBytes)
      case NoEffect(a)         => a
      case ChainEffect(eff, f) =>
        eff match {
          case Read          => runFile(f(input.getLines().mkString(",")))
          case Write(content) => runFile(f(output.write(content.getBytes)))
          case NoEffect(v)         => runFile(f(v))
          case ChainEffect(eff1, f1) =>
            runFile(eff1.flatMap(a => f1(a).flatMap(f)))
        }
    }
  }

  def loopWhile[A](p: ConsoleEffect[A])(f: A => Boolean): ConsoleEffect[A] = {
    p.flatMap((a: A) => if (f(a)) loopWhile(p)(f) else NoEffect(a))
  }

  implicit class PrinterOps[A, B](p: ConsoleEffect[A]) {
    def map(f: A => B): ConsoleEffect[B] = ConsoleEffect.map(p)(f)
    def flatMap(f: A => ConsoleEffect[B]): ConsoleEffect[B] = ConsoleEffect.flatMap(p)(f)
    def run(implicit runFile : Boolean = false) = if(runFile) ConsoleEffect.runFile(p) else ConsoleEffect.run(p)
    def loopWhile(f: A => Boolean): ConsoleEffect[A] = ConsoleEffect.loopWhile(p)(f)
  }
}
