import java.util.*;


/**
 * Problem - http://www.lintcode.com/en/problem/binary-tree-paths/
 */
public class BinaryTreePaths {


/**
 * DATA STRUCTURE CHOICES:
 * Info:  Need last-in-first-out (LIFO aka Stack)
 * Assumptions:        + Don't need the overhead of thread safety
                        + Don't care about excessive expansion
                + Don't need null elements
 * Stack - Would do the trick but Javadoc says to use Deque, which is not really a
Stack but rather a double-ended Queue, so it can be either LIFO or FIFO.
 * ConcurrentLinkedDeque - Don't need thread safety
 * LinkedBlockingDeque - Excessive expansion is not a concern
 * LinkedList - Don't need null elements; LinkedList has node allocation overhead
 * Collections.asLifoQueue(Deque<T> deque) - Don't need to order elements in LIFO order;
Just need to add to tail and remove from tail.
 * ArrayDeque - Winner by process of elimination


 * REFERENCES:
 * http://heai.me/2014-10/markdown-java-stack-linkedlist-arraydeque/
 * http://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
 * http://bit.ly/1H4jzfj
 * https://publicobject.com/2010/07/07/caliper_confirms_reality_linked_list_vs_array_list/
 *
 * @todo Can we estimate the size of the Collection?
 */
private Deque<String> deque = new ArrayDeque<String>();


/**
 * DATA STRUCTURE CHOICES:
 * Decision is made for us b/c problem specifies that we need to return List<String>.
 *
 * @todo Can we estimate the size of the Collection?
 */
private List<String> result = new ArrayList<String>();


/**
 * @param root the root of the binary tree
 * @return all root-to-leaf paths
 */
public List<String> binaryTreePaths(TreeNode root) {
// Write your code here
if(root == null) return result;
        
deque.add(Integer.toString(root.val));
// If we reached the end of a leaf, save it to the results
if(root.left == null && root.right == null) {
        // @todo Verify requirement of "->" as the delimiter. Can it be ", " instead?
String joined = String.join("->", deque);
result.add(joined);
}
else {
if(root.left != null) {
binaryTreePaths(root.left);
}


if(root.right != null) {
binaryTreePaths(root.right);
}
}
        
// We are done processing this node, so crawl back up the tree
deque.removeLast();
return result;
}


        /**
         * Clear the results to reuse a BinaryTreePaths instance
         *
 * @todo What if the caller forgets to call clear before he calls binaryTreePaths again?
         */
public void clear() {
        result.clear();
        }


        /**
         * Test Cases
         */
        public static void main(String[] args) {
                System.out.println("Once upon a problem…");


BinaryTreePaths btp = new BinaryTreePaths();
List<String> result;


// 1st Test Case
//       1
//     /   \
//    2     3
//      \
//       5
TreeNode tree1 = new TreeNode(1);
                tree1.left = new TreeNode(2);
                tree1.left.right = new TreeNode(5);
                tree1.right = new TreeNode(3);


result = btp.binaryTreePaths(tree1);
System.out.println("\n1st Test Case:");
System.out.println(result);
btp.clear();


        // 2nd Test Case
//       -11
TreeNode tree2 = new TreeNode(-11);


result = btp.binaryTreePaths(tree2);
System.out.println("\n2nd Test Case:");
System.out.println(result);
btp.clear();
// 3rd Test Case
//      1.1
//     /   \
//    2    -3
//         /
//        5
//          \
//          2345
TreeNode tree3 = new TreeNode('a');
                tree3.left = new TreeNode(2);
                tree3.right = new TreeNode(-3);
                tree3.right.left = new TreeNode(5);
tree3.right.left.right = new TreeNode(2345);


result = btp.binaryTreePaths(tree3);
System.out.println("\n3rd Test Case:");
System.out.println(result);
btp.clear();
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


/* A walk through the call tree:
btp(1)
        deque = 1
        btp(2)
                deque = 1,2
                btp(5)
                        deque = 1,2,5
                        result = [1->2->5]
                        deque = 1,2
                deque = 1
        btp(3)
                deque = 1,3
                result = [1->2->5, 1->3]
                deque = 1
        deque = ""
*/