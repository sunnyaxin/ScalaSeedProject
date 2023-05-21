package problems

object RemoveDuplicatesList {
  def main(args: Array[String]): Unit = {
    println(
      Solution
        .deleteDuplicates(
          new ListNode(1, new ListNode(1, new ListNode(2)))
        )
        .toString
    ) // [1, 2]
    println(
      Solution.deleteDuplicates(
        new ListNode(
          1,
          new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3))))
        )
      )
    ) // [1, 2, 3]
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
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
