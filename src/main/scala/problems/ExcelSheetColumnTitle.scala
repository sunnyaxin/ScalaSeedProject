package problems

import scala.annotation.tailrec

object ExcelSheetColumnTitle {
  def main(args: Array[String]): Unit = {
    println(Solution.convertToTitle(1)) //A
    println(Solution.convertToTitle(28)) //AB
    println(Solution.convertToTitle(701)) //ZY
    println(Solution.convertToTitle(10000)) //NTP
    println(Solution.convertToTitle(52)) //AZ
  }

  object Solution {
    private val DIC: Map[Int, Char] = Map(
      1 -> 'A',
      2 -> 'B',
      3 -> 'C',
      4 -> 'D',
      5 -> 'E',
      6 -> 'F',
      7 -> 'G',
      8 -> 'H',
      9 -> 'I',
      10 -> 'J',
      11 -> 'K',
      12 -> 'L',
      13 -> 'M',
      14 -> 'N',
      15 -> 'O',
      16 -> 'P',
      17 -> 'Q',
      18 -> 'R',
      19 -> 'S',
      20 -> 'T',
      21 -> 'U',
      22 -> 'V',
      23 -> 'W',
      24 -> 'X',
      25 -> 'Y',
      26 -> 'Z'
    )

    def convertToTitle0(columnNumber: Int): String = {
      if (columnNumber <= 26) return DIC(columnNumber).toString
      if (columnNumber % 26 == 0) return convertToTitle(columnNumber / 26 - 1) + "Z"
      convertToTitle(columnNumber / 26) + DIC(columnNumber % 26)
    }

    def convertToTitle(n: Int): String = {
      val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      @tailrec
      def convertRecursively(n: Int, result: String): String = {
        if (n == 0) result
        else convertRecursively((n - 1) / 26, alphabet.charAt((n - 1) % 26) + result)
      }

      convertRecursively(n, "")
    }
  }
}
