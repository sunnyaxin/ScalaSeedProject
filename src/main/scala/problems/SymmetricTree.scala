package problems

import problems.common.TreeNode

object SymmetricTree {
  def main(args: Array[String]): Unit = {
    println(
      Solution.isSymmetric(
        new TreeNode(
          1,
          new TreeNode(2, new TreeNode(3), new TreeNode(4)),
          new TreeNode(2, new TreeNode(4), new TreeNode(3))
        )
      )
    ) // true
    println(
      Solution.isSymmetric(
        new TreeNode(
          1,
          new TreeNode(2, null, new TreeNode(3)),
          new TreeNode(2, null, new TreeNode(3))
        )
      )
    ) // false
    println(
      Solution.isSymmetric(
        new TreeNode(
          1,
          new TreeNode(2, new TreeNode(2)),
          new TreeNode(2, new TreeNode(2))
        )
      )
    ) //false
  }

  object Solution {

    private def isMirror(left: TreeNode, right: TreeNode): Boolean =
      (left, right) match {
        case (null, null)          => true
        case (null, _) | (_, null) => false
        case (l, r)
            if l.value == r.value &&
              isMirror(l.left, r.right) &&
              isMirror(l.right, r.left) =>
          true
        case (_, _) => false
      }

    def isSymmetric(root: TreeNode): Boolean = {
      isMirror(root.left, root.right)
    }
  }
}
