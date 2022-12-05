package example.arena

import cats.effect.{ExitCode, IO, IOApp}

import java.time.Instant
import scala.concurrent.duration.DurationInt

object Test extends IOApp{
  object Definition {
    def printThread: IO[Unit] =
      for {
        _ <- IO(println(Instant.now()))
        _ <- IO.sleep(5 seconds)
        thread <- IO(Thread.currentThread().getName)
        _ <- IO(println(s"current thread is $thread"))
        _ <- IO(println(Instant.now()))
      } yield ()
  } // 一个阻塞IO的定义

  object Scope1 {
    def operation: IO[Unit] = Definition.printThread.fork
  } // 单独的fork IO 1

  object Scope2 {
    def operation: IO[Unit] = Definition.printThread.fork
  } //  单独的fork IO 2

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- Scope1.operation
    _ <- Scope2.operation
    _ <- Definition.printThread
  } yield ExitCode.Success
}
