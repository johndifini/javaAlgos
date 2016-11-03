import java.util.*;


/**
 * Given two arrays, write a function to compute their intersection.
 * e.g. Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 * 
 * @see https://www.careercup.com/question?id=63460
 * @see http://www.lintcode.com/en/problem/intersection-of-two-arrays/
 * @see http://www.lintcode.com/en/problem/intersection-of-two-arrays-ii/
 */
public class ArrayIntersector {


        public static List<Integer> findIntersection(ArrayList<Integer> a, ArrayList<Integer> b) {
                // @todo Validate input (e.g. check for null & empty)


// Data Structure Choice:
// Since the results must be unique, let's use a Set.
// In addition, let's use an ordered Set (TreeSet) so that we can short
// circuit the searching if we find that the lists don't overlap.
                TreeSet<Integer> setA = new TreeSet<Integer>(a);
TreeSet<Integer> setB = new TreeSet<Integer>(b);


// Since we already got rid of dupes by using a set,
// we can use a List for the results.
// Note: We can't assume a minimum size for initialization?
                List<Integer> result = new ArrayList<Integer>();


                // If the "highest" of one set is less than the "lowest" of the other,
                // nothing to do (i.e. no overlap)
                // e.g. a=[1,2,3]; b=[4,5] or a=[11,22,33]; b=[1,1,1]
                if(setA.last() < setB.first() || setB.last() < setA.first()) {
                        System.out.println("nothing for us to do");
                        return result;
                }


                // For each elem in the smaller Set, check if it exists in the other set
                if(setA.size() < setB.size()) {
                        // @todo Should we make a method for this?
                        for(int elem : setA) {
                                if(setB.contains(elem)) {
                                        result.add(elem);
                                }
                        }
                }
                else {
                        for(int elem : setB) {
                                if(setA.contains(elem)) {
                                        result.add(elem);
                                }
                        }
                }


                return result;
        }


        /**
         * Test Cases
         */
        public static void main(String[] args) {
                System.out.println("Once upon a problem...");
                ArrayList<Integer> a;
                ArrayList<Integer> b;
                List<Integer> result;
                
                // Test Case - example
                a = new ArrayList<Integer>(Arrays.asList(1,2,2,1));
                b = new ArrayList<Integer>(Arrays.asList(2,2));
                System.out.println("\n\na<"+a+">");
                System.out.println("b<"+b+">");
                result = findIntersection(a, b);
                System.out.println("result<"+result+">");


                // Test Case - random
                a = new ArrayList<Integer>(Arrays.asList(5,12,2,2,-1));
                b = new ArrayList<Integer>(Arrays.asList(999,2,999,5,10,-1,0));
                System.out.println("\n\na<"+a+">");
                System.out.println("b<"+b+">");
                result = findIntersection(a, b);
                System.out.println("result<"+result+">");


                // Test Case - no overlap
                a = new ArrayList<Integer>(Arrays.asList(5,12,2,2,1));
                b = new ArrayList<Integer>(Arrays.asList(22,99,55));
                System.out.println("\n\na<"+a+">");
                System.out.println("b<"+b+">");
                result = findIntersection(a, b);
                System.out.println("result<"+result+">");


                // Test Case - no overlap case 2
                a = new ArrayList<Integer>(Arrays.asList(5,12,12,12,10));
                b = new ArrayList<Integer>(Arrays.asList(2,4,1));
                System.out.println("\n\na<"+a+">");
                System.out.println("b<"+b+">");
                result = findIntersection(a, b);
                System.out.println("result<"+result+">");


                // Test Case - equal sizes
                a = new ArrayList<Integer>(Arrays.asList(0,0,0));
                b = new ArrayList<Integer>(Arrays.asList(0,0,0));
                System.out.println("\n\na<"+a+">");
                System.out.println("b<"+b+">");
                result = findIntersection(a, b);
                System.out.println("result<"+result+">");
        }


}