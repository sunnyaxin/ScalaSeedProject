package problems

object InorderTraversal {
  def main(args: Array[String]): Unit = {
    println(
      Solution.inorderTraversal(
        new TreeNode(
          1,
          null,
          new TreeNode(2, new TreeNode(3, null, null), null)
        )
      )
    ) //[1,3,2]
    println(Solution.inorderTraversal(null)) //[]
    println(Solution.inorderTraversal(new TreeNode(1))) //[1]
  }

  class TreeNode(
      _value: Int = 0,
      _left: TreeNode = null,
      _right: TreeNode = null
  ) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  object Solution {

    def inorderTraversal(root: TreeNode): List[Int] = {
      if(root == null) return Nil
      inorderTraversal(root.left) ++ List[Int](root.value) ++ inorderTraversal(root.right)
    }
  }
}
