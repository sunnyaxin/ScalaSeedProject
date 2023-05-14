package problems

import scala.collection.mutable.ArrayBuffer

object RemoveElement {
  def main(args: Array[String]): Unit = {
    println(Solution.removeElement(Array(3, 2, 2, 3), 3))
    println(Solution.removeElement(Array(0, 1, 2, 2, 3, 0, 4, 2), 2))
  }

  object Solution {
    def removeElement1(nums: Array[Int], `val`: Int): Int = {
      val result = ArrayBuffer[Int]()
      for (i <- nums if i != `val`) {
        result += i
      }
      for (i <- result.indices) {
        nums(i) = result(i)
      }
      result.length
    }

    def removeElement(nums: Array[Int], `val`: Int): Int = {
      val result = nums.filter(_ != `val`)
      for (i <- result.indices) {
        nums(i) = result(i)
      }
      result.length
    }
  }
}
