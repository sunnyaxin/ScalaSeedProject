package problems

object ExcelSheetColumnNumber {
  def main(args: Array[String]): Unit = {
    println(Solution.titleToNumber1("A")) //1
    println(Solution.titleToNumber1("AB")) //28
    println(Solution.titleToNumber1("ZY")) //701
  }

  object Solution {

    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    def titleToNumber0(columnTitle: String): Int = {
      var result = 0
      for (i <- columnTitle.indices) {
        result += Math.pow(26, i).toInt * (alphabet.indexOf(
          columnTitle.charAt(columnTitle.length - i - 1).toString
        ) + 1)
      }
      result
    }

    def titleToNumber1(s: String): Int = {
      var result = 0
      for (i <- s.indices) {
        result += Math.pow(26, i).toInt * (s.charAt(s.length - i - 1) - 'A' + 1)
      }
      result
    }

    def titleToNumber(s: String): Int = {
      s.foldLeft(0)((base, ch) => base * 26 + (ch - 'A' + 1))
    }
  }
}
