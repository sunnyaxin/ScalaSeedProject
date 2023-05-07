package problems

import cats.effect.{ExitCode, IO, IOApp}

object LongestCommonPrefix extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(
        println(Solution.longestCommonPrefix(Array("flower", "flow", "flight")))
      )
      _ <- IO(
        println(Solution.longestCommonPrefix(Array("dog", "racecar", "car")))
      )
      _ <- IO(
        println(Solution.longestCommonPrefix(Array("ab", "a", "aadgfasdf")))
      )
    } yield ExitCode.Success

  object Solution {
    def longestCommonPrefix(strs: Array[String]): String = {
      if (strs.isEmpty) return ""
      var prefix = ""
      val min = strs.minBy(x => x.length)
      for (i <- 0 until min.length) {
        for (j <- strs.indices) {
          if (min.charAt(i) != strs(j).charAt(i))
            return prefix
          else if (j == strs.length - 1) prefix += min.charAt(i)
        }
      }
      prefix
    }
  }
}
