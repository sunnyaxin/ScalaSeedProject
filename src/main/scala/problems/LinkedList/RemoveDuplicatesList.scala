package problems.LinkedList

object RemoveDuplicatesList {
  def main(args: Array[String]): Unit = {
    println(
      Solution
        .deleteDuplicates(
          ListNode(1, ListNode(1, ListNode(2)))
        )
        .toString
    ) // [1, 2]
    println(
      Solution.deleteDuplicates(
        ListNode(
          1,
          ListNode(1, ListNode(2, ListNode(3, ListNode(3))))
        )
      )
    ) // [1, 2, 3]
  }

  object Solution {
    def deleteDuplicates(head: ListNode): ListNode = {
      var cur = head
      val result = cur
      while (cur != null) {
        while (cur.next != null && cur.next.x == cur.x) {
          cur.next = cur.next.next
        }
        cur = cur.next
      }
      result
    }
  }
}
