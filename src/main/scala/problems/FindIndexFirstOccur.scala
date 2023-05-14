package problems

object FindIndexFirstOccur {
  def main(args: Array[String]): Unit = {
    println(Solution.strStr("sadbutsad", "sad"))
    println(Solution.strStr("leetcode", "leeto"))
    println(Solution.strStr("a", "a"))
  }

  object Solution {
    def strStr1(haystack: String, needle: String): Int = {
      if (needle.length > haystack.length) return -1
      for (i <- needle.indices) {
        for (
          j <- haystack.indices
          if haystack(j) == needle(i) && needle.length + j <= haystack.length
        ) {
          if (haystack.substring(j, needle.length + j).equals(needle))
            return j
        }
      }
      -1
    }

    def strStr(haystack: String, needle: String): Int = {
      if (haystack.contains(needle)) {
        haystack
          .sliding(needle.length)
          .zipWithIndex
          .filter(x => x._1 == needle)
          .map(x => x._2)
          .next()
      } else {
        -1
      }
    }
  }
}
