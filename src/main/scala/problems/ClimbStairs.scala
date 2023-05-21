package problems

import scala.annotation.tailrec

object ClimbStairs {
  def main(args: Array[String]): Unit = {
    println(Solution.climbStairs(2)) //2
    println(Solution.climbStairs(3)) //3
    println(Solution.climbStairs(4)) //5
  }

  object Solution {

    def climbStairs0(n: Int): Int = {
      if (n == 1) return 1
      if (n == 2) return 2
      climbStairs0(n - 1) + climbStairs0(n - 2)
    }

    @tailrec
    private def climb(n: Int, cur: Int, pre: Int): Int = {
      if (n == 0) cur
      else climb(n - 1, cur + pre, cur)
    }

    def climbStairs(n: Int): Int = {
      climb(n, 1, 0)
    }
  }
}
