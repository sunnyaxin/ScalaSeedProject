package problems

import cats.effect.{ExitCode, IO, IOApp}

import scala.annotation.tailrec

object PalindromeNumber extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(println(Solution.isPalindrome(121)))
      _ <- IO(println(Solution.isPalindrome(-121)))
      _ <- IO(println(Solution.isPalindrome(10)))
      _ <- IO(println(Solution.isBetterPalindrome(121)))
      _ <- IO(println(Solution.isBetterPalindrome(-121)))
      _ <- IO(println(Solution.isBetterPalindrome(10)))
    } yield ExitCode.Success

  object Solution {
    def isPalindrome(x: Int): Boolean = {
      val str = String.valueOf(x)
      if (str.equals(str.reverse))
        true
      else false
    }

    @tailrec
    def reverse(x: Int, y: Int): Int =
      if (x == 0) y else reverse(x / 10, y * 10 + x % 10)

    def isBetterPalindrome(x: Int): Boolean =
      if (x < 0) false
      else if (x < 10) true
      else x == reverse(x, 0)
  }
}
