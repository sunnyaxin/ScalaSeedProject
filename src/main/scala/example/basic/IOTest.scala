package example.basic

object IOTest {

  def main(args: Array[String]): Unit = {
    add(1, 2).run()
  }

  def log(v: String): IO[Unit] = {
    IO(println(v))
  }

  def add(x: Double, y: Double): IO[Double] = {
    val r = IO[Double](x + y)
    r.flatMap(x => {
      log(s"result is $x")
    }.map(_ => x))
  }
}

class IO[A](f: => A) {

  def map[B](f1: A => B): IO[B] = {
    IO[B](f1(f))
  }

  def flatMap[B](f2: A => IO[B]): IO[B] = {
    f2(f)
  }

  def run(): Unit = {
    f
  }
}

object IO {
  def apply[A](f: => A): IO[A] = new IO(f)
}