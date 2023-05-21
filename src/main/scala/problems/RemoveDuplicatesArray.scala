package problems

import scala.collection.mutable.ArrayBuffer

object RemoveDuplicatesArray {
  def main(args: Array[String]): Unit = {
    println(Solution.removeDuplicates(Array(1, 1, 2))) //2
    println(Solution.removeDuplicates(Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4))) //5
  }

  object Solution {
    def removeDuplicates1(nums: Array[Int]): Int = {
      val result = new ArrayBuffer[Int]
      for (num <- nums if !result.contains(num)) {
        result += num
      }
      for (i <- result.indices) {
        nums(i) = result(i)
      }
      result.length
    }

    def removeDuplicates2(nums: Array[Int]): Int = {
      val newNums = nums.distinct
      for (i <- newNums.indices) {
        nums(i) = newNums(i)
      }
      newNums.length
    }

    def removeDuplicates(nums: Array[Int]): Int = {
      val newNums = nums.distinct
      newNums.zipWithIndex.foreach { case (item, index) => nums(index) = item }
      newNums.length
    }
  }
}
