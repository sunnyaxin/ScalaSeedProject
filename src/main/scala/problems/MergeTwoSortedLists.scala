package problems

import cats.effect.{ExitCode, IO, IOApp}
import problems.common.ListNode

import scala.annotation.tailrec

object MergeTwoSortedLists extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(
        println(
          Solution.mergeTwoLists(
            new ListNode(1, new ListNode(2, new ListNode(4))),
            new ListNode(1, new ListNode(3, new ListNode(4)))
          )
        )
      )
      _ <- IO(
        println(
          Solution.mergeTwoLists(null, null)
        )
      )
      _ <- IO(
        println(
          Solution.mergeTwoLists(new ListNode(0), null)
        )
      )
    } yield ExitCode.Success

  object Solution {

    @tailrec
    def merge(list1: ListNode, list2: ListNode, temp: ListNode): ListNode = {
      if (list1 == null) { temp.next = list2; return temp }
      if (list2 == null) { temp.next = list1; return temp }
      if (list1.x < list2.x) {
        temp.next = list1; merge(list1.next, list2, list1)
      } else { temp.next = list2; merge(list1, list2.next, list2) }
    }

    def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
      val init = new ListNode()
      merge(list1, list2, init)
      init.next
    }
  }
}
