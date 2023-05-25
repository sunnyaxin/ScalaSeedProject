package problems.Tree

object BalancedBinaryTree {
  def main(args: Array[String]): Unit = {
    println(
      Solution.isBalanced(
        TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
      )
    ) //true
    println(
      Solution.isBalanced(
        TreeNode(
          1,
          TreeNode(2, TreeNode(3, TreeNode(4), TreeNode(4)), TreeNode(3)),
          TreeNode(2)
        )
      )
    ) // false
  }

  object Solution {
    def depth(node: TreeNode): Int = {
      if (node == null) return 0
      1 + Math.max(depth(node.left), depth(node.right))
    }

    def isBalanced(root: TreeNode): Boolean = {
      if (root == null) return true
      if (Math.abs(depth(root.left) - depth(root.right)) > 1) return false
      isBalanced(root.left) && isBalanced(root.right)
    }
  }
}
