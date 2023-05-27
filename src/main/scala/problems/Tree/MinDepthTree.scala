package problems.Tree

object MinDepthTree {
  def main(args: Array[String]): Unit = {
    println(
      Solution.minDepth(
        TreeNode(
          3,
          TreeNode(9),
          TreeNode(20, TreeNode(15), TreeNode(7, TreeNode(4)))
        )
      )
    ) //2
    println(
      Solution.minDepth(
        TreeNode(
          2,
          null,
          TreeNode(3, null, TreeNode(4, null, TreeNode(5, null, TreeNode(6))))
        )
      )
    ) //5
  }

  object Solution {
    def minDepth(root: TreeNode): Int = {
      if (root == null) return 0
      if (root.left == null) return 1 + minDepth(root.right)
      if (root.right == null) return 1 + minDepth(root.left)
      1 + Math.min(minDepth(root.left), minDepth(root.right))
    }
  }
}
