package problems

object ValidPalindrome {
  def main(args: Array[String]): Unit = {
    println(Solution.isPalindrome("A man, a plan, a canal: Panama")) //true
    println(Solution.isPalindrome("race a car")) //false
    println(Solution.isPalindrome(" ")) //true
  }

  object Solution {
    def isPalindrome0(s: String): Boolean = {
      val alphanumeric = "abcdefghijklmnopqrstuvwxyz0123456789"
      val result = s.toLowerCase.filter(alphanumeric.contains(_))
      result == result.reverse
    }

    def isPalindrome(s: String): Boolean = {
      val filtered = s.filter(_.isLetterOrDigit)
      filtered.reverse equalsIgnoreCase filtered
    }
  }
}
