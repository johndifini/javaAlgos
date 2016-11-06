import java.util.*;


/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * Example
 * Given a binary tree as follow:
 *    1
 *  /   \ 
 * 2     3
 *     /   \
 *    4     5
 * The maximum depth is 3.
 * 
 * @see http://www.lintcode.com/en/problem/maximum-depth-of-binary-tree/
 * @see http://articles.leetcode.com/maximum-height-of-binary-tree/
 * @see https://en.wikipedia.org/wiki/Tree_traversal#Iterative_Traversal
 */
public class BinTreeMaxDepth {
   /**
    * Pseudocode for Level-order Traversal:
    * levelorder(root)
    *   q ← empty queue
    *   q.enqueue(root)
    *   while (not q.isEmpty())
    *     node ← q.dequeue()
    *     visit(node)
    *     if (node.left ≠ null)
    *       q.enqueue(node.left)
    *     if (node.right ≠ null)
    *       q.enqueue(node.right)
    */
   public static int maxDepth(TreeNode root) {
      int depth = 0;


      if (root == null)
         return depth;
    
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      q.add(root);
      q.add(new TreeNode(-1)); // our tag node
      while (!q.isEmpty()) {
         TreeNode node = q.remove();


         // visit(node)
         if (node.val == -1) {
            depth++;
            if (!q.isEmpty()) // avoid an infinite loop
               q.add(new TreeNode(-1));
         }
         
         if (node.left != null)
            q.add(node.left);


         if (node.right != null)
            q.add(node.right);
      }   
      
      return depth;
   }


   /**
    * Test Cases
    */
   public static void main(String[] args) {
      System.out.println("Once upon a problem...");
      int result;


      // Test Case
      //       1
      //     /   \
      //    2     3
      //        /   \
      //       4     5
      TreeNode tree1 = new TreeNode(1);
      tree1.left = new TreeNode(2);
      tree1.right = new TreeNode(3);
      tree1.right.left = new TreeNode(4);
      tree1.right.right = new TreeNode(5);


      result = -1;
      result = maxDepth(tree1);
      System.out.println("\nTest Case '12345'; result<"+result+">");
      System.out.println(result);


      // Test Case
      //       1
      //     /   \
      //    2     3
      //      \
      //       5
      TreeNode tree2 = new TreeNode(1);
      tree2.left = new TreeNode(2);
      tree2.left.right = new TreeNode(5);
      tree2.right = new TreeNode(3);


      result = -1;
      result = maxDepth(tree2);
      System.out.println("\nTest Case '1235'; result<"+result+">");
      System.out.println(result);


      // Test Case
      //       0
      TreeNode tree3 = new TreeNode(0);


      result = -1;
      result = maxDepth(tree3);
      System.out.println("\nTest Case '0'; result<"+result+">");
      System.out.println(result);


      // Test Case
      //       1
      //     /   \
      //    2     3
      //         /
      //        5
      //          \
      //           9
      TreeNode tree4 = new TreeNode('1');
      tree4.left = new TreeNode(2);
      tree4.right = new TreeNode(3);
      tree4.right.left = new TreeNode(5);
      tree4.right.left.right = new TreeNode(9);


      result = -1;
      result = maxDepth(tree4);
      System.out.println("\nTest Case 'zigzag'; result<"+result+">");
      System.out.println(result);


   }


   /**
    * TreeNode definition provided in problem statement
    */
   private static class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
      }
   }


}


/*
A Walk Thru an Example:
       1
     /   \
    2     3
         /
        5
          \
           9
initially q = [1 -1]


node = 1;  q = [-1]
-1?: ---
left: q = [-1 2]
right: q = [-1 2 3]


node = -1;  q = [2 3]
-1?: depth = 1;  q = [2 3 -1]
left: ---
right: ---


node = 2;  q = [3 -1]
-1?: ---
left: ---
right: ---


node = 3;  q = [-1]
-1?: ---
left: q = [-1 5]
right: ---


node = -1;  q = [5]
-1?: depth = 2;  q = [5 -1]
left: ---
right: ---


node = 5;  q = [-1]
-1?: ---
left: ---
right: q = [-1 9]


node = -1;  q = [9]
-1?: depth = 3;  q = [9 -1]
left: ---
right: ---


node = 9;  q = [-1]
-1?: ---
left: ---
right: ---


node = -1;  q = empty
-1?: depth = 4; q ---
left: ---
right: ---
*/