package example.arena2

import scala.language.higherKinds

object FireForget {

  trait FireForgetSyntax[F[_], A] {
    def fork(f: F[A]): F[Unit]
  }

  implicit class FireForgetOps[F[_], A](f: F[A])(implicit ff: FireForgetSyntax[F, A]) {
    def fork: F[Unit] = ff.fork(f)
  }
}
