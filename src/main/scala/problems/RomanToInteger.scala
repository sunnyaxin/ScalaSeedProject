package problems

import cats.effect.{ExitCode, IO, IOApp}

object RomanToInteger extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(println(Solution.betterRomanToInt("MCMXCIV")))
      _ <- IO(println(Solution.betterRomanToInt("LVIII")))
      _ <- IO(println(Solution.betterRomanToInt("III")))
    } yield ExitCode.Success

  object Solution {
    def betterRomanToInt(s: String): Int = {
      val ss = s.replace("IV", "IIII")
        .replace("IX", "VIIII")
        .replace("XL", "XXXX")
        .replace("XC", "LXXXX")
        .replace("CD", "CCCC")
        .replace("CM", "DCCCC")

      ss.foldLeft(0) {
        case (num, 'I') => num + 1
        case (num, 'V') => num + 5
        case (num, 'X') => num + 10
        case (num, 'L') => num + 50
        case (num, 'C') => num + 100
        case (num, 'D') => num + 500
        case (num, 'M') => num + 1000
        case (num, _)   => num + 0
      }
    }
  }
}
