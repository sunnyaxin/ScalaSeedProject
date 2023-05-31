package problems

import scala.collection.mutable.ArrayBuffer

object SingleNumber {
  def main(args: Array[String]): Unit = {
    println(Solution.singleNumber(Array(2, 2, 1))) //1
    println(Solution.singleNumber(Array(4, 1, 2, 1, 2))) //4
    println(Solution.singleNumber(Array(1))) //1
  }

  object Solution {
    def singleNumber0(nums: Array[Int]): Int = {
      val temp = ArrayBuffer[Int]()
      for (i <- nums) {
        if (temp.contains(i)) temp -= i
        else temp += i
      }
      temp(0)
    }

    def singleNumber(nums: Array[Int]): Int = nums.fold(0)(_ ^ _)
  }
}
