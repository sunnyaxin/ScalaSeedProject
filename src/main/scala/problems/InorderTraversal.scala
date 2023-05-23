package problems

import problems.common.TreeNode

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

  object Solution {

    def inorderTraversal(root: TreeNode): List[Int] = {
      if(root == null) return Nil
      inorderTraversal(root.left) ++ List[Int](root.value) ++ inorderTraversal(root.right)
    }
  }
}
