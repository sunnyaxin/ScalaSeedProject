package problems

import scala.collection.Searching.search

object SearchInsertPosition {
  def main(args: Array[String]): Unit = {
    println(Solution.searchInsert(Array(1, 3, 5, 6), 5)) //2
    println(Solution.searchInsert(Array(1, 3, 5, 6), 2)) //1
    println(Solution.searchInsert(Array(1, 3, 5, 6), 7)) //4
    println(Solution.searchInsert(Array(1, 3, 5, 6), 0)) //0
    println(Solution.searchInsert(Array(1, 3), 2)) //1
  }

  object Solution {
    def searchInsert1(nums: Array[Int], target: Int): Int = {
      var min = 0
      var max = nums.length - 1
      var mid = 0
      var position = 0
      while (min <= max) {
        mid = (min + max) / 2
        if (nums(mid) == target)
          return mid
        else if (nums(mid) < target) {
          min = mid + 1
          position = mid + 1
        } else {
          max = mid - 1
          position = mid
        }
      }
      if (position < 0) 0 else position
    }

    def searchInsert(nums: Array[Int], target: Int): Int = {
      nums.search(target).insertionPoint
    }
  }
}
