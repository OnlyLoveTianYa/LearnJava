package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h
 * 个节点。
 *
 * 示例:
 *
 * 输入: 1 / \ 2   3 / \  / 4  5 6
 *
 * 输出: 6
 *
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
public class CountNodes {


  public int countNodes(TreeNode root) {
    int tier = 1;
    boolean isLastTier = false;
    List<TreeNode> tierNodes = new ArrayList<>();
    List<TreeNode> nextTierNodes = new ArrayList<>();
    int haveOne = 0;
    tierNodes.add(root);
    if (!isLastTier) {
      nextTierNodes.clear();
      for (TreeNode node : tierNodes) {
        TreeNode left = node.left;

        TreeNode right = node.right;
        if (left != null && right != null) {
          nextTierNodes.add(node);
        } else if (left == null) {
          isLastTier = true;
        } else {
          isLastTier = true;
          haveOne = 1;
        }
      }
      if (!isLastTier) {
        tierNodes = nextTierNodes;
        tier = tier + 1;
      }
    }
    return calculateFactorial(tier) + haveOne + tierNodes.size();
  }

  private static int calculateFactorial(int n) {
    int count = 1;
    while (n > 0) {
      count *= n;
      n--;
    }
    return count;
  }

  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
