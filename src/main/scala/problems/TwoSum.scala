package problems

import cats.effect.{ExitCode, IO, IOApp}

import scala.annotation.tailrec

object TwoSum extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(
        println(
          Solution.twoSum(Array(2, 7, 11, 15), 9).mkString("Array(", ", ", ")")
        )
      )
      _ <- IO(
        println(
          Solution.twoSum(Array(3, 2, 4), 6).mkString("Array(", ", ", ")")
        )
      )
      _ <- IO(
        println(Solution.twoSum(Array(3, 3), 6).mkString("Array(", ", ", ")"))
      )
      _ <- IO(
        println(
          Solution.betterTwoSum(Array(2, 7, 11, 15), 9).mkString("Array(", ", ", ")")
        )
      )
      _ <- IO(
        println(
          Solution.betterTwoSum(Array(3, 2, 4), 6).mkString("Array(", ", ", ")")
        )
      )
      _ <- IO(
        println(Solution.betterTwoSum(Array(3, 3), 6).mkString("Array(", ", ", ")"))
      )
    } yield ExitCode.Success

  object Solution {
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      for (i <- nums.indices) {
        for (j <- i + 1 until nums.length) {
          if (nums(i) + nums(j) == target) return Array(i, j)
        }
      }
      Array.empty[Int]
    }

    @tailrec
    def find(nums: Array[Int], i: Int, j: Int, t: Int): Array[Int] =
      if (i >= nums.length) Array.empty[Int]
      else if (j >= nums.length) find(nums, i + 1, i + 2, t)
      else if (nums(i) + nums(j) == t) Array(i, j)
      else find(nums, i, j + 1, t)

    def betterTwoSum(nums: Array[Int], target: Int): Array[Int] =
      find(nums, 0, 1, target)
  }
}
