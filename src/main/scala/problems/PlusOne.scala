package problems

import scala.annotation.tailrec

object PlusOne {
  def main(args: Array[String]): Unit = {
    println(Solution.plusOne1(Array(1, 2, 3)).mkString("Array(", ", ", ")"))
    println(Solution.plusOne1(Array(4, 3, 2, 1)).mkString("Array(", ", ", ")"))
    println(Solution.plusOne1(Array(9)).mkString("Array(", ", ", ")"))
    println(
      Solution
        .plusOne1(Array(9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
        .mkString("Array(", ", ", ")")
    )
  }

  object Solution {
    def cal1(d: List[Int]): List[Int] =
      d match {
        case 9 :: Nil            => List(0, 1)
        case 9 :: second :: list => 0 :: cal1(second :: list)
        case head :: list        => head + 1 :: list
        case _                   => Nil
      }

    def plusOne1(digits: Array[Int]): Array[Int] = {
      cal1(digits.toList.reverse).reverse.toArray
    }

    @tailrec
    def call2(d: List[Int], sum: List[Int]): List[Int] =
      d match {
        case 9 :: Nil     => sum ++ List(0, 1)
        case 9 :: _ :: _  => call2(d.tail, 0 :: sum)
        case head :: list => (sum :+ head + 1) ++ list
        case _            => Nil
      }

    def plusOne(digits: Array[Int]): Array[Int] = {
      call2(digits.toList.reverse, List()).reverse.toArray
    }
  }
}
