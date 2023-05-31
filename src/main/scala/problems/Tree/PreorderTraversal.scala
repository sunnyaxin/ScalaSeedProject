package problems.Tree

object PreorderTraversal {
  def main(args: Array[String]): Unit = {
    println(
      Solution.preorderTraversal(TreeNode(1, null, TreeNode(2, TreeNode(3))))
    ) //[1,2,3]
    println(Solution.preorderTraversal(TreeNode(1))) //[1]
    println(Solution.preorderTraversal(null)) //[]
  }

  object Solution {
    def preorderTraversal(root: TreeNode): List[Int] = {
      if (root == null) return Nil
      List(root.value) ++
        preorderTraversal(root.left) ++
        preorderTraversal(root.right)
    }
  }
}
