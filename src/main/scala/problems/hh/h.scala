package problems.hh

object h {
  def main(args: Array[String]): Unit = {
//    println(getLongestPeriod(List(1, 0, 1, 1, 0, 1, 1, 1, 0, 1))) //3
//    println(getLongestPeriod(List(1, 0, 0, 0, 1))) //1
//    println(getLongestPeriod(List(0, 0, 0, 0))) //0
//    println(getLongestPeriod(List(1, 1, 1, 1, 1))) //5
//    println(getLongestPeriod(List(1, 0, 0, 0, 1))) //1
//    println(getLongestPeriod(List(0, 0, 0, 0, 1))) //1
//    println(
//      getLongestPeriodMonth(
//        List(
//          "Jan",
//          "Feb",
//          "Mar",
//          "Apr",
//          "May"
//        ),
//        List(1, 1, 1, 1, 0)
//      )
//    ) //4, (Jun - Apr)
//    println(
//      getLongestPeriodMonth(
//        List("Jan"),
//        List(1)
//      )
//    ) //1, (Jun - Jun)
//    println(
//      getLongestPeriodMonth(
//        List("Jan"),
//        List(0)
//      )
//    ) //0, ( - )
//    println(
//      getLongestPeriodMonth(
//        List(
//          "Jan",
//          "Feb",
//          "Mar",
//          "Apr",
//          "May",
//          "Jun",
//          "Jul",
//          "Aug",
//          "Sep",
//          "Oct"
//        ),
//        List(1, 0, 1, 1, 0, 1, 1, 1, 0, 1)
//      )
//    ) //3, (Jun - Aug)

    println(
      getLargestProfit(
        List(
          "Jan",
          "Feb",
          "Mar",
          "Apr",
          "May",
          "Jun",
          "Jul",
          "Aug",
          "Sep",
          "Oct"
        ),
        List(1, 0, 9, 1, 0, 2, 2, 2, 0, 1)
      )
    ) //10, (Mar - Apr)
  }

  def getLongestPeriod(records: List[Int]): Int = {
    var longestPeriod = 0
    var currentCount = 0
    for (i <- records) {
      if (i == 1) {
        currentCount += 1
      } else {
        currentCount = 0
      }
      longestPeriod =
        if (currentCount > longestPeriod) currentCount else longestPeriod
    }
    longestPeriod
  }

  def getLongestPeriodMonth(
      months: List[String],
      records: List[Int]
  ): (Int, String) = {
    var longestPeriod = 0
    var currentCount = 0
    var endIndex = 0
    for (i <- records.indices) {
      if (records(i) == 1) {
        currentCount += 1
      } else {
        currentCount = 0
      }
      longestPeriod = if (currentCount > longestPeriod) {
        endIndex = i
        currentCount
      } else longestPeriod
    }
    val startIndex = endIndex - longestPeriod + 1
    if (longestPeriod == 0) (0, "")
    else
      (longestPeriod, "(" + months(startIndex) + "-" + months(endIndex) + ")")
  }

  def getLargestProfit(
      months: List[String],
      records: List[Int]
  ): (Int, String) = {
    var largestProfit = 0
    var currentProfit = 0
    var endIndex = 0
    var longestPeriod = 0
    var currentCount = 0
    for (i <- records.indices) {
      if (records(i) == 0) {
        currentProfit = 0
      } else {
        currentProfit += records(i)
        longestPeriod += 1
      }
      largestProfit = if (currentProfit > largestProfit) {
        endIndex = i
        currentProfit
      } else largestProfit
      longestPeriod = if (currentCount > longestPeriod) {
        endIndex = i
        currentCount
      } else longestPeriod
    }
    val startIndex = endIndex - longestPeriod + 1
    if (largestProfit == 0) (0, "")
    else
      (largestProfit, "(" + months(startIndex) + "-" + months(endIndex) + ")")
  }
}
