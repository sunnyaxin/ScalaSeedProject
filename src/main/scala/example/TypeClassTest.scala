package example

object TypeClassTest {

  def main(args: Array[String]): Unit = {
    println(division(1, 2))
    println(division(1, 0))

    import ResponseInstances._
    import ShowSyntax._

    val a: Response = RightResponse(222)
    println(a.show)

    val b: Response = ErrorResponse("Test add method", (0, 0))
    println(b.show)

    println(1.show)

    //    implicitly[Show[Response]].show(RightResponse(1))
  }

  def division(x: Double, y: Double): Response =
    y match {
      case 0 => ErrorResponse("y is zero", (x, y))
      case _ => RightResponse(x / y)
    }
}

sealed trait Response

case class RightResponse(result: Double) extends Response

case class ErrorResponse(error: String, input: (Double, Double)) extends Response

trait Show[A] {
  def show(v: A): String
}

//Nice to have
object ShowSyntax {

    implicit class ShowOps[A](v: A) {
      def show(implicit showA: Show[A]): String = showA.show(v)
    }

//  implicit class ShowOps[A](v:Fixture.getDepthPublication A)(implicit p: Show[A]) {
//  implicit class ShowOps[A: Show](v: A) {
//    def show(): String = implicitly[Show[A]].show(v)
//  }
}

object ResponseInstances {
  implicit val responseShowInstance: Show[Response] = new Show[Response] {
    override def show(res: Response): String = res match {
      case RightResponse(v) => v.toString
      case ErrorResponse(error, (x, y)) => s"$error, input is ($x, $y)"
    }
  }

  //多态
  implicit val intShowInstance: Show[Int] = new Show[Int] {
    override def show(res: Int): String = res.toString
  }

  //    implicit val responseShowInstance: Show[Response] = {
  //      case RightResponse(v) => v.toString
  //      case ErrorResponse(error, (x, y)) => s"$error, input is ($x, $y)"
  //    }
}