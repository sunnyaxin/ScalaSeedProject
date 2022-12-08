package example.arena

import cats.effect.{ExitCode, IO, IOApp}
import example.arena.Test.Definition

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object FiberTest extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))
    val printThread = Definition.printThread

    for {
      fiber <- printThread.startOn(ec)
      _ <- fiber.join
    } yield ExitCode.Success
  }
}
