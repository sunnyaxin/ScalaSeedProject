package example.arena3

object Main extends App {

  def write(content: String): Printer[Unit] = Writer(content)
  def read: Printer[String] = Reader()

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
  // 进行上述行为的循环直到输入空值
}

sealed trait Printer[A]
case class Reader() extends Printer[String]
case class Writer(content: String) extends Printer[Unit]
case class Pure[A](v: A) extends Printer[A]
case class OrderedEffect[A, B](eff: Printer[A], f: A => Printer[B]) extends Printer[B]

object Printer {
  def map[A, B](p: Printer[A])(f: A => B): Printer[B] = flatMap(p)((a: A) => Pure(f(a)))

  def flatMap[A, B](p: Printer[A])(f: A => Printer[B]): Printer[B] =
    p match {
      case Reader() => OrderedEffect(Reader(), (a: A) => f(a))
      case Writer(content) => OrderedEffect(Writer(content), (a: A) => f(a))
      case Pure(a) => f(a)
      case OrderedEffect(eff, g) => OrderedEffect(eff, (a: Any) => flatMap(g(a))(f)) //OrderedEffect(p, f)
    }

  def run[A](p: Printer[A]): A =
    p match {
      case Reader() => scala.io.StdIn.readLine()
      case Writer(content) => println(content)
      case Pure(a) => a
      case OrderedEffect(eff, f) => run(f(run(eff)))
    }

  def loopWhile[A](p: Printer[A])(f: A => Boolean): Printer[A] =
    OrderedEffect(p, (a: A) => if (f(a)) loopWhile(flatMap(p)(a => Pure(a)))(f) else Pure(a))

  implicit class PrinterOps[A, B](p: Printer[A]) {
    def map(f: A => B): Printer[B] = Printer.map(p)(f)
    def flatMap(f: A => Printer[B]): Printer[B] = Printer.flatMap(p)(f)
    def run = Printer.run(p)
    def loopWhile(f: A => Boolean): Printer[A] = Printer.loopWhile(p)(f)
  }
}
