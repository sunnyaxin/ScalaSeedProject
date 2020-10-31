package session.datatype

object ListExample {
  def main(args: Array[String]): Unit = {
    testNode()
    println(List(1, 2, 3))
    //    testIdentifier()
  }

  private def testNode(): Unit = {
    val oneNode = Node(1, EndNode)
    val twoNode = Node(2, oneNode)
    val node = Node(3, Node(2, Node(1, EndNode)))
    println(node)
  }

  private def testIdentifier(): Unit = {
    EndNode.makeList(1) //Node(1, EndNode)
    EndNode makeList 1
    EndNode makeList 1 makeList 2 //2,1,End
    val a = EndNode makeList 1 makeList 2 makeList 3
    val b = 3 `makeList:` (2 `makeList:` (1 `makeList:` EndNode))
    println(a)
    println(b)
    println(3 :: 2 :: 1 :: Nil)
  }

  sealed trait List {
    def makeList(v: Int): List = Node(v, this)

    def `makeList:`(v: Int): List = Node(v, this)
  }

  case class Node(v: Int, next: List) extends List

  case object EndNode extends List

}
