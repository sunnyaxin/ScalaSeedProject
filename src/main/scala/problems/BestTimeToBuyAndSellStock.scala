package problems

object BestTimeToBuyAndSellStock {
  def main(args: Array[String]): Unit = {
    println(Solution.maxProfit(Array(7, 1, 5, 3, 6, 4))) //5
    println(Solution.maxProfit(Array(7, 6, 4, 3, 1))) //0
  }

  object Solution {

    //todo Memory Limit Exceeded
    def maxProfit_no(prices: Array[Int]): Int = {
      var diff = 0
      for (i <- prices.indices) {
        for (
          j <- i + 1 until prices.length
          if prices(j) > prices(i)
        ) {
          diff = Math.max(prices(j) - prices(i), diff)
        }
      }
      diff
    }

    //todo Memory Limit Exceeded
    def maxProfit_no2(prices: Array[Int]): Int = {
      var max = 0
      for (i <- 0 until prices.length - 1) {
        val cur = prices(i)
        val next = prices.drop(i + 1).max
        if (next > cur) {
          max = Math.max(next - cur, max)
        }
      }
      max
    }

    def maxProfit(prices: Array[Int]): Int = {
      var buy = Int.MaxValue
      var profit = 0

      for (p <- prices) {
        if (p > buy) {
          profit = Math.max(profit, p - buy)
        }
        buy = Math.min(p, buy)
      }
      profit
    }
  }
}
