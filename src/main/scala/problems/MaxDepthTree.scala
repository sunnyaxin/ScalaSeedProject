package problems

import problems.common.TreeNode

import scala.collection.mutable

object MaxDepthTree {
  def main(args: Array[String]): Unit = {
    println(
      Solution.maxDepth(
        TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
      )
    ) //3
    println(
      Solution.maxDepth(
        TreeNode(1, null, TreeNode(2))
      )
    ) //2
  }

  object Solution {
    def maxDepth1(root: TreeNode): Int = {
      if (root == null) return 0
      val queue = mutable.Queue[(TreeNode, Int)]()
      var depth = 1
      queue.enqueue((root, depth))

      while (queue.nonEmpty) {
        val (node, d) = queue.dequeue()
        depth = d
        if (node.left != null) {
          queue.enqueue((node.left, depth + 1))
        }
        if (node.right != null) {
          queue.enqueue((node.right, depth + 1))
        }
      }
      depth
    }

    def maxDepth(root: TreeNode): Int = {
      if (root == null) return 0
      1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }
  }
}
