package problems

import scala.annotation.tailrec

object AddBinary {
  def main(args: Array[String]): Unit = {
    println(Solution.addBinary("11", "1")) //100
    println(Solution.addBinary("1010", "1011")) //10101
  }

  object Solution {

    @tailrec
    private def add(
        a: List[Int],
        b: List[Int],
        sum: List[Int],
        flag: Int
    ): List[Int] = {
      (a, b) match {
        case (Nil, Nil) => if (flag == 1) 1 :: sum else sum
        case (aa :: _, bb :: _) =>
          val result = aa + bb + flag
          if (result >= 2) {
            add(a.tail, b.tail, (result - 2) :: sum, 1)
          } else {
            add(a.tail, b.tail, result :: sum, 0)
          }
        case (_, _) => List(0)
      }
    }

    def addBinary2(a: String, b: String): String = {
      val diff = Math.abs(a.length - b.length)
      var aa = a.map(_.toString.toInt).toList
      var bb = b.map(_.toString.toInt).toList
      if (a.length < b.length) {
        aa = List.fill(diff)(0) ++ aa
      } else if (a.length > b.length) {
        bb = List.fill(diff)(0) ++ bb
      }
      val sum = add(aa.reverse, bb.reverse, List(), 0)
      sum.mkString
    }

    def addBinary1(a: String, b: String): String = {
      val diff = Math.abs(a.length - b.length)
      var aa = a.map(_.toString.toInt).toList
      var bb = b.map(_.toString.toInt).toList
      var flag = 0
      var sum = List[Int]()
      if (a.length < b.length) {
        aa = List.fill(diff)(0) ++ aa
      } else if (a.length > b.length) {
        bb = List.fill(diff)(0) ++ bb
      }
      aa = aa.reverse
      bb = bb.reverse
      for (i <- aa.indices) {
        val result = aa(i) + bb(i) + flag
        if (result >= 2) {
          sum = (result - 2) :: sum
          flag = 1
        } else {
          sum = result :: sum
          flag = 0
        }
      }
      sum = if (flag == 1) 1 :: sum else sum
      sum.mkString
    }

    def addBinary(a: String, b: String): String = {
      (BigInt(a,2)+BigInt(b,2)).toString(2)
    }
  }
}
