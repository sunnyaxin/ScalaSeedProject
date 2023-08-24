package example

import cats.effect.{ExitCode, IO, IOApp}

object RaiseerrorTest extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- IO.println("1")
    _ <- IO.println("2")
    _ <- IO.raiseError(new Error("error")).recover { case _: Throwable => ()}
    _ <- IO.println("4")
  } yield ExitCode.Success
}
