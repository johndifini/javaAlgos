import java.util.*;


/**
 * *Kadane's Algorithm*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * @see https://leetcode.com/problems/maximum-subarray/
 * @see https://discuss.leetcode.com/category/61/maximum-subarray
 * @see https://www.youtube.com/results?search_query=kadane%27s+algorithm
 * @see https://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class MaxSubarraySumFinder {
   public static int findMaxSubarraySum(int[] array) {
      // @todo validate input for empty and null


      int curMax = array[0];
      int overallMax = array[0];
      // We can't use an 'enhanced' for loop b/c we need to start at the 2nd index
      for (int i = 1; i<array.length; i++) {
         curMax = Math.max(array[i], array[i]+curMax);
         overallMax = Math.max(curMax, overallMax);
      }


      return overallMax;
   }


   public static void main(String[] args) {
       int result;


       int testCase1[] = {-2,1,-3,4,-1,2,1,-5,4};
       result = findMaxSubarraySum(testCase1);
       System.out.println("testCase<"+Arrays.toString(testCase1)+"> result<"+result+">");


       int testCase2[] = {-2};
       result = findMaxSubarraySum(testCase2);
       System.out.println("testCase<"+Arrays.toString(testCase2)+"> result<"+result+">");


       int testCase3[] = {0,0};
       result = findMaxSubarraySum(testCase3);
       System.out.println("testCase<"+Arrays.toString(testCase3)+"> result<"+result+">");
   }
}


/* A Walk Thru an Example:
   LOGIC:
      curMax = array[0];
      overallMax = array[0];
      for (int i = 1; i<array.length; i++)
         curMax = Math.max(array[i], array[i]+curMax);
         overallMax = Math.max(curMax, overallMax);


[-2,1,-3,4,-1,2,1,-5,4]
      array[i] array[i]+curMax curMax overallMax
INIT:                              -2         -2
LOOP:        1              -1      1          1
            -3              -2     -2          1
             4               2      4          4
            -1               3      3          4
             2               5      5          5
             1               6      6          6
            -5               1      1          6
             4               5      5          6
*/