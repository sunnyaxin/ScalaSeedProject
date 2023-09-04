package session.FPConcepts

import cats.effect.{ExitCode, IO, IOApp}

object ADTExample extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO.println(division0(1, 2))
      _ <- IO.println(division0(1, 0))
      _ <- IO.println(division(1, 2))
      _ <- IO.println(division(1, 0))
    } yield ExitCode.Success

  def division0(x: Double, y: Double): Double = x / y

  sealed trait Response
  case class Result(v: Double) extends Response
  case class DivisionError(error: String, input: (Double, Double))
      extends Response

  def division(x: Double, y: Double): Response =
    if (y == 0)
      DivisionError("exception happen", (x, y))
    else
      Result(x / y)
}
