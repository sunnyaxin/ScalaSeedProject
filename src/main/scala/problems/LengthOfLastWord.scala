package problems

object LengthOfLastWord {
  def main(args: Array[String]): Unit = {
    println(Solution.lengthOfLastWord("Hello World")) //5
    println(Solution.lengthOfLastWord("   fly me   to   the moon  ")) //4
    println(Solution.lengthOfLastWord("luffy is still joyboy")) //6
    println(Solution.lengthOfLastWord("luffy is still  haha")) //4
  }

  object Solution {
    def lengthOfLastWord1(s: String): Int = {
      val ss = s.trim.split(" ")
      ss(ss.length - 1).length
    }

    def lengthOfLastWord(s: String): Int = {
      s.split(" ").last.length
    }
  }
}
