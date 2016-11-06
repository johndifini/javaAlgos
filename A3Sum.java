import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * e.g. Given array S = {-1 0 1 2 -1 -4}, A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 * 
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * 
 * @see https://en.wikipedia.org/wiki/3SUM
 * @see http://www.lintcode.com/en/problem/3sum/
 * @see http://www.programcreek.com/2012/12/leetcode-3sum/
 * @see https://www.glassdoor.com/Interview/Google-Interview-RVW12456862.htm
 * @see https://www.careercup.com/question?id=10033132
 */
public class A3Sum {
    public static List<ArrayList<Integer>> find3Sums(int[] array) {
        // @todo validate input for null or len < 3

        // Cannot make an assumption about the minimum list size
        List<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();

        Arrays.sort(array);
        System.out.println("Sorted input<"+Arrays.toString(array)+">");

        for(int i=0; i<array.length-2; i++) {
            int leftIdx = i+1; //Always start w/ the least # that we haven't checked yet
            int rightIdx = array.length-1; //Always start w/ the greatest #
            while(leftIdx < rightIdx) {
                Integer a = array[i]; Integer b = array[leftIdx]; Integer c = array[rightIdx];
                System.out.println("Evaluating a<"+a+">, b<"+b+">, c<"+c+">");
                int sum = a+b+c;
                if(sum == 0) {
                    ArrayList<Integer> triplet = new ArrayList<Integer>(3);
                    triplet.add(a); triplet.add(b); triplet.add(c);
                    results.add(triplet);

                    // We are done with the curr. value of i b/c
                    // if we increment leftIdx, we incr. the sum &
                    // if we decrement rightIdx, we decr. the sum)
                    break;
                }
                else if(sum > 0)
                    // The sum is too big, so need to decrease the greatest #
                    rightIdx--;
                else
                    // The sum is too small, so need to increase the least #
                    leftIdx++;
            }
        }

        return results;
    }


    /**
     * Test Cases
     */
    public static void main(String[] args) {
        System.out.println("Once upon a problem...");
        List<ArrayList<Integer>> result;

        int[] input = {-1, 0, 1, 2, -1, -4};
        result = find3Sums(input);
        System.out.println(result);

    }


/*
A Walk Thru the Algorithm
  0   1   2  3  4  5
{-4, -1, -1, 0, 1, 2}

i=0/-4
leftIdx=1/-1 (always start w/ the least # that we haven't checked yet (ie i+1))
rightIdx=5/2 (always start w/ the greatest # (ie index 5))
sum=-3 (The sum is too small, so we need to increase the least # (ie increment leftIdx))

i=0/-4 (no change)
leftIdx=2/-1
rightIdx=5/2 (no change)
sum=-3 (The sum is too small, so we need to increase the least # (ie increment leftIdx))

i=0/-4 (no change)
leftIdx=3/0
rightIdx=5/2 (no change)
sum=-2 (The sum is too small, so we need to increase the least # (ie increment leftIdx))

i=0/-4 (no change)
leftIdx=4/1
rightIdx=5/2 (no change)
sum=-1 (exhausted all possibilities with 0/-4 (ie increment i))

i=1/-1
leftIdx=2/-1 (always start w/ the least # that we haven't checked yet (ie i+1))
rightIdx=5/2 (always start w/ the greatest # (ie index 5))
sum=0 (BINGO! We are done with 1/-1 b/c if we increment leftIdx,
       we incr. the sum & if we decrement rightIdx, we decr. the sum)

i=2/-1
leftIdx=3/0 (always start w/ the least # that we haven't checked yet (ie i+1))
rightIdx=5/2 (always start w/ the greatest # (ie index 5))
sum=1 (The sum is too big, so we need to decrease the greatest # (ie decrement rightIdx))

i=2/-1 (no change)
leftIdx=3/0 (no change)
rightIdx=4/1
sum=0 (BINGO! (see prev. BINGO explanation))

i=3/0
leftIdx=4/1 (always start w/ the least # that we haven't checked yet (ie i+1))
rightIdx=5/2 (always start w/ the greatest # (ie index 5))
sum=3 (DONE! We exhausted all possibilities)
*/

}