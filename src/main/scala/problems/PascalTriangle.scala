package problems

object PascalTriangle {
  def main(args: Array[String]): Unit = {
//    println(Solution.generate(5)) // [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//    println(Solution.generate(1)) //[[1]]
    println(Solution.getRow(3)) //[1,3,3,1]
    println(Solution.getRow(0)) //[1]
    println(Solution.getRow(1)) //[1,1]
  }

  object Solution {
    def generate(numRows: Int): List[List[Int]] = {
      val triangle = Array.ofDim[Array[Int]](numRows)

      for (i <- 0 until numRows) {
        triangle(i) = Array.ofDim[Int](i + 1)
        triangle(i)(0) = 1
        triangle(i)(i) = 1
        for (j <- 0 until i if i > 1 && j > 0) {
          triangle(i)(j) = triangle(i - 1)(j - 1) + triangle(i - 1)(j)
        }
      }
      triangle.map(_.toList).toList
    }

    private def row(prev: List[Int]): List[Int] = {
      1 :: prev.sliding(2).map(_.sum).toList ::: List(1)
    }

    def getRow(rowIndex: Int): List[Int] = {
      if (rowIndex == 0) return List(1)
      var id = 1
      var prev = List(1, 1)
      while (id < rowIndex) {
        prev = row(prev)
        id += 1
      }
      prev
    }
  }
}
