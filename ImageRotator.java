import java.util.*;


/**
 * You are given an nxn 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Example
 * Given a matrix
 *   [1,2,3]
 *   [4,5,6]
 *   [7,8,9]
 * rotate it by 90 degrees (clockwise), return
 *   [7,4,1]
 *   [8,5,2]
 *   [9,6,3]
 * 
 * The key to the problem is finding a way to swap elements,
 * which requires an intermediate step as defined by the following LeetCode post.
 * The intermediate step involves reversing the row order. The swap step
 * involves _diagonally_ swapping all but the 1st element. 
 *   Input     Inter. Step   Swap Step
 * [1,2,3]      [7,8,9]      [7,4,1]
 * [4,5,6]  =>  [4,5,6]  =>  [8,5,2]
 * [7,8,9]      [1,2,3]      [9,6,3]
 *
 * Now, let's evaluate the each swap (the headings represent positions):
 *  Input     Inter. Step    01<->10    02<->20    12<->21
 * [1,2,3]      [7,8,9]      [7,4,9]    [7,4,1]    [7,4,1]
 * [4,5,6]  =>  [4,5,6]  =>  [8,5,6] => [8,5,6] => [8,5,2]
 * [7,8,9]      [1,2,3]      [1,2,3]    [9,2,3]    [9,6,3]
 *
 * @see https://discuss.leetcode.com/topic/6796/a-common-method-to-rotate-the-image
 * @see https://discuss.leetcode.com/topic/9744/ac-java-in-place-solution-with-explanation-easy-to-understand
 * @see http://www.geeksforgeeks.org/turn-an-image-by-90-degree/
 * @see http://www.lintcode.com/en/problem/rotate-image/
 */
public class ImageRotator {
   public static void rotate(int[][] matrix) {
      // Reverse rows - Since we are *swapping* rows,
      // only do this for half of them
      for (int r = 0; r<matrix.length/2; r++) {
         int tmp[] = matrix[r];
         matrix[r] = matrix[matrix.length-1-r];
         matrix[matrix.length-1-r] = tmp;
      }


      // Diagonal swap
      // Visit each row, but skip cols by row+1
      for (int r = 0; r<matrix.length; r++) {
         for (int c = r+1; c<matrix[r].length; c++) {
             int tmp = matrix[r][c];
             matrix[r][c] = matrix[c][r];
             matrix[c][r] = tmp;
         }
      }
   }


   public static void main(String[] args) {
      int[][] threeXthree = new int[][] {
         {1,2,3},
         {4,5,6},
         {7,8,9}
      };
      System.out.println("\nBefore:");
      print(threeXthree);
      rotate(threeXthree);
      System.out.println("After:");
      print(threeXthree);


      int[][] fourXfour = new int[][] {
         {1,2,3,4},
         {5,6,7,8},
         {9,10,11,12},
         {13,14,15,16}
      };
      System.out.println("\nBefore:");
      print(fourXfour);
      rotate(fourXfour);
      System.out.println("After:");
      print(fourXfour);


      int[][] twoXtwo = new int[][] {
         {1,2},
         {4,5}
      };
      System.out.println("\nBefore:");
      print(twoXtwo);
      rotate(twoXtwo);
      System.out.println("After:");
      print(twoXtwo);
   }


   public static void print(int[][] matrix) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i<matrix[0].length; i++) {
         for (int j = 0; j<matrix[i].length; j++) {
            sb.append(matrix[i][j]);
         }
         System.out.println(sb);
         sb.delete(0, sb.length());
      }
   }


}