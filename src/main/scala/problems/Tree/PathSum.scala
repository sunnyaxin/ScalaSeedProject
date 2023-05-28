package problems.Tree

object PathSum {
  def main(args: Array[String]): Unit = {
    println(
      Solution.hasPathSum(
        TreeNode(
          5,
          TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2))),
          TreeNode(8, TreeNode(13), TreeNode(4, null, TreeNode(1)))
        ),
        22
      )
    ) // true
    println(
      Solution.hasPathSum(TreeNode(1, TreeNode(2), TreeNode(3)), 5)
    ) //false
    println(Solution.hasPathSum(null, 0)) //false
  }

  object Solution {

    private def sum(node: TreeNode, s: Int, pathSum: List[Int]): List[Int] = {
      var result = pathSum
      (node.left, node.right) match {
        case (null, null) =>
          result = (s + node.value) :: result
          return result
        case (null, right) =>
          result = sum(right, node.value + s, result)
        case (left, null) =>
          result = sum(left, node.value + s, result)
        case (left, right) =>
          result = sum(left, node.value + s, result)
          result = sum(right, node.value + s, result)
      }
      result
    }

    def hasPathSum1(root: TreeNode, targetSum: Int): Boolean = {
      if (root == null) return false
      val sums = sum(root, 0, Nil)
      if (sums.contains(targetSum)) true
      else false
    }

    def hasPathSum(root: TreeNode, targetSum: Int): Boolean = {
      if (root == null) return false
      if (root.left == null && root.right == null)
        targetSum == root.value
      else
        hasPathSum(root.left, targetSum - root.value) || hasPathSum(
          root.right,
          targetSum - root.value
        )
    }
  }
}
