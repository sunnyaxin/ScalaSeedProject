package problems

import cats.effect.{ExitCode, IO, IOApp}

object MergeAlternately extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(println(Solution.mergeAlternately("abc", "pqr")))
      _ <- IO(println(Solution.mergeAlternately("ab", "pqrs")))
      _ <- IO(println(Solution.mergeAlternately("abcd", "pq")))
      _ <- IO(println(Solution.betterMergeAlternately("abc", "pqr")))
      _ <- IO(println(Solution.betterMergeAlternately("ab", "pqrs")))
      _ <- IO(println(Solution.betterMergeAlternately("abcd", "pq")))
    } yield ExitCode.Success

  object Solution {
    def mergeAlternately(word1: String, word2: String): String = {
      val word1Arr = word1.toList
      val word2Arr = word2.toList
      val word1Length = word1Arr.length
      val word2Length = word2Arr.length
      var result = ""
      val lessLength =
        if (word1Length < word2Length) word1Length else word2Length
      for (i <- 0 until lessLength) {
        result = result + word1Arr(i) + word2Arr(i)
      }
      val moreArr =
        if (word1Length < word2Length) word2Arr.drop(word1Length).mkString
        else word1Arr.drop(word2Length).mkString
      result + moreArr
    }

    def betterMergeAlternately(word1: String, word2: String): String = {
      val n = Math.min(word1.length, word2.length)
      var result = ""

      for (i <- 0 until n) {
        result = result + word1.charAt(i)
        result = result + word2.charAt(i)
      }
      result + word1.substring(n) + word2.substring(n)
    }
  }
}
