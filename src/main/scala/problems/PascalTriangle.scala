package problems

object PascalTriangle {
  def main(args: Array[String]): Unit = {
    println(Solution.generate(5)) // [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    println(Solution.generate(1)) //[[1]]
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
  }
}
