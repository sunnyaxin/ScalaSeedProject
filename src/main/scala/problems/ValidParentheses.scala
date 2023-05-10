package problems

import cats.effect.{ExitCode, IO, IOApp}

import scala.collection.mutable

object ValidParentheses extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(println(Solution.isValid("()")))
      _ <- IO(println(Solution.isValid("()[]{}")))
      _ <- IO(println(Solution.isValid("(]")))
      _ <- IO(println(Solution.isValid("{[]}")))
      _ <- IO(println(Solution.isValid(")(")))
      _ <- IO(println(Solution.isValid("(){}}{")))
    } yield ExitCode.Success

  object Solution {
    def isValid(s: String): Boolean = {
      if (s.length % 2 != 0 || s.isEmpty) false
      else {
        val tempStack = mutable.Stack[Char]()
        for (i <- s.toCharArray) {
          i match {
            case '}' if tempStack.nonEmpty && tempStack.head == '{' =>
              tempStack.pop
            case ']' if tempStack.nonEmpty && tempStack.head == '[' =>
              tempStack.pop
            case ')' if tempStack.nonEmpty && tempStack.head == '(' =>
              tempStack.pop
            case _ => tempStack.push(i)
          }
        }
        if (tempStack.isEmpty) true else false
      }
    }
  }
}
