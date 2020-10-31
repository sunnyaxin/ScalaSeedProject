package session.datatype

object EitherExample {
  def main(args: Array[String]): Unit = {
    //origin
    //    println(div(8, 2))
    //    println(div(1, 0))

    //RichResult
    println(richDiv(8, 2))
    println(richDiv(1, 0))

    //map, flatmap
    val a: RichResult = Result(1)
    println(a.map(_ + 1))
    println(a.flatMap(richDiv(4, _)))
  }

  def div(x: Int, y: Int): Int = x / y

  sealed trait RichResult {
    def map(f: Int => Int): RichResult = this match {
      case ErrorResult(error) => ErrorResult(error)
      case Result(v) => Result(f(v))
    }

    def flatMap(f: Int => RichResult): RichResult = this match {
      case ErrorResult(error) => ErrorResult(error)
      case Result(v) => f(v)
    }
  }

  case class ErrorResult(error: String) extends RichResult

  case class Result(v: Int) extends RichResult

  def richDiv(x: Int, y: Int): RichResult = y match {
    case 0 => ErrorResult("OMG, y should NOT 0!!!")
    case _ => Result(x / y)
  }
}
