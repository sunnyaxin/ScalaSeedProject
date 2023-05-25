package problems.Tree

object SameTree {
  def main(args: Array[String]): Unit = {
    println(
      Solution.isSameTree(
        TreeNode(1, TreeNode(2), TreeNode(3)),
        TreeNode(1, TreeNode(2), TreeNode(3))
      )
    ) //true
    println(
      Solution.isSameTree(
        TreeNode(1, TreeNode(2)),
        TreeNode(1, null, TreeNode(2))
      )
    ) //false
    println(
      Solution.isSameTree(
        TreeNode(1, TreeNode(2), TreeNode(1)),
        TreeNode(1, TreeNode(1), TreeNode(2))
      )
    ) //false
    println(
      Solution.isSameTree(
        TreeNode(1, TreeNode(1)),
        TreeNode(1, null, TreeNode(1))
      )
    ) //false
  }

  object Solution {
    private def printInorderTree(t: TreeNode): List[Int] = {
      if (t == null) return List(-1)
      printInorderTree(t.left) ++ List(t.value) ++ printInorderTree(t.right)
    }

    private def printPreorderTree(t: TreeNode): List[Int] = {
      if (t == null) return List(-1)
      List(t.value) ++ printPreorderTree(t.left) ++ printPreorderTree(t.right)
    }

    private def printPostorderTree(t: TreeNode): List[Int] = {
      if (t == null) return List(-1)
      printPostorderTree(t.left) ++ printPostorderTree(t.right) ++ List(t.value)
    }

    def isSameTree1(p: TreeNode, q: TreeNode): Boolean = {
      printInorderTree(p) == printInorderTree(q) &&
      printPreorderTree(p) == printPreorderTree(q) &&
      printPostorderTree(p) == printPostorderTree(q)
    }

    def isSameTree(p: TreeNode, q: TreeNode): Boolean = {
      if (p == null && q == null) return true
      if ((p == null && q != null) || (p != null && q == null)) return false
      if (p.value != q.value) return false
      isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
  }
}
