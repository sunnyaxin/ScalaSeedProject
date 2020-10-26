package example

object Hello extends Greeting with App {
//  println(greeting)
//  val aa = List(1, 2, 3)
//  println(aa :: List(3, 3))
//  println(1 :: aa)
//  println(List(3, 3) :: aa)
//  println(aa ::: List(5, 5))
//  println(List(5, 5) ::: aa)
//
//  val x = {
//    case User(a, b) => User.apply(a, b)
//  }
//  val aa = 23
//  val test = aa match {
//    case 123 => "hello"
//  }
//  println(test)
}

trait Greeting {
  lazy val greeting: String = "hello"

  def getData: Reader[String, List[Int]] = {
    for {
      trans <- Reader.ask[String]
    } yield {
      println(s"$trans - fetching data")
      List(1, 2, 3)
    }
  }

  def getDataRow: Reader[String, List[Int]] = {
    Reader.ask[String]
      .map[List[Int]](str => {
        println(s"$str - fetching data")
        List(1, 2, 3)
      })
  }
}

case class User(name: String, age: Int)

case class Reader[A, B](run: A => B) {
  def map[C](f: B => C): Reader[A, C] = {
    val runN: A => C = (x: A) => f(run(x))
    Reader(runN)
  }

  def flatMap[C](f: B => Reader[A, C]): Reader[A, C] = {
    val runN: A => C = (x: A) => f(run(x)).run(x)
    Reader(runN)
  }
}

object Reader {
  def ask[A]: Reader[A, A] = Reader[A, A](identity)
}