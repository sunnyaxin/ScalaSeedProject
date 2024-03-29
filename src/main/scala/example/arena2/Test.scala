package example.arena2

import cats.effect.implicits.asyncOps
import cats.effect.kernel.Async
import cats.effect.{ExitCode, IO, IOApp}
import example.arena2.FireForget._
import example.arena2.Test.IOInstance.fork

import java.time.Instant
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.language.{higherKinds, postfixOps}
import cats.implicits._

object Test extends IOApp {
  object Definition {
    def printThread: IO[Unit] =
      for {
        _ <- IO(println(Instant.now()))
        _ <- IO.sleep(3 seconds)
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

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- Scope1.operation
      _ <- Scope2.operation
      _ <- Scope2.operation
      _ <- Definition.printThread
    } yield ExitCode.Success

  object IOInstance {
    val ec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool)

    implicit def fork[T, F[_]: Async]: FireForgetSyntax[F, T] = (f: F[T]) => for {
      _ <- f.startOn(ec)
    } yield ()
  }
}
