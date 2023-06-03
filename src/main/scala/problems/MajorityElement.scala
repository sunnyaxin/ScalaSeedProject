package problems

object MajorityElement {
  def main(args: Array[String]): Unit = {
    println(Solution.majorityElement(Array(3, 2, 3))) //3
    println(Solution.majorityElement(Array(2, 2, 1, 1, 1, 2, 2))) //2
  }

  object Solution {
    def majorityElement(nums: Array[Int]): Int = {
      var prev = 0
      var repeat = 0
      for (i <- nums.sorted) {
        if (i == prev) repeat += 1
        else {
          prev = i
          repeat = 1
        }
        if (repeat > nums.length / 2)
          return i
      }
      0
    }
  }
}
