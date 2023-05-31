package problems.Tree

object PostorderTraversal {
  def main(args: Array[String]): Unit = {
    println(
      Solution.postorderTraversal(TreeNode(1, null, TreeNode(2, TreeNode(3))))
    ) //[3,2,1]
    println(Solution.postorderTraversal(TreeNode(1))) //[1]
    println(Solution.postorderTraversal(null)) //[]
  }

  object Solution {
    def postorderTraversal(root: TreeNode): List[Int] = {
      if (root == null) return Nil
      postorderTraversal(root.left) ++ postorderTraversal(root.right) ++ List(
        root.value
      )
    }
  }
}
