package problems

object MySqrt {
  def main(args: Array[String]): Unit = {
    println(Solution.mySqrt(4)) //2
    println(Solution.mySqrt(8)) //2
  }

  object Solution {
    def mySqrt(x: Int): Int = {
      if (x == 1) return 1
      var mid = 0
      var low = 0
      var high = x
      while (low <= high) {
        mid = (low + high) / 2
        if (mid == 0 || mid == x / mid) {
          return mid
        }
        if (mid > x / mid) {
          high = mid - 1
        }
        if (mid < x / mid) {
          low = mid + 1
        }
      }
      low - 1
    }
  }
}
