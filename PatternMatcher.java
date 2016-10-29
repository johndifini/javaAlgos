import java.util.*;


/**
 * Given a string pattern of 0s, 1s, and ?s (wildcards), generate all 0-1 strings that match this pattern.
 * e.g. 1?00?101 -> [10000101, 10001101, 11000101, 11001101].
 *
 * @see http://bit.ly/2f0AZkh
 * @see https://www.careercup.com/question?id=20308668
 * @see http://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
 */
public class PatternMatcher {
        // @todo Is ArrayList the most efficient Collection for this case?
private List<String> result = new ArrayList<String>();


public List<String> matchPattern(String expr) {
        // @todo Validate user input. What if the string contains something other than 0s, 1s, or ?s


// @todo We know exactly how many results to expect based on the nbr of ?s
// (2 to the number of ?s; e.g. 1? = 1 results, 2?s = 4 results, 3?s = 8 results).
// But is looping thru the string to find the nbr of ?s worth the performance
// gain of setting the collections size? If so, consider using a plain-old array.


        int pos = expr.indexOf('?');
                if(pos < 0) {
result.add(expr);
                return result;
                }


        // String is immutable, thus it doesn't have a simple setCharAt method
        // like StringBuilder has. But if we were to use StringBuilder,
// we would have to be careful with its "pass-by-reference" when making
// recursive calls and when adding it to the result.
String modStr = expr.substring(0, pos) + "0" + expr.substring(pos+1);
        System.out.println("calling matchPattern0<"+modStr+">");
        matchPattern(modStr);


        //expr.setCharAt(pos, '1');
        modStr = expr.substring(0, pos) + "1" + expr.substring(pos+1);
System.out.println("calling matchPattern1<"+modStr+">");
        matchPattern(modStr);


                return result;
}


        /**
         * Clear the results to reuse the instance
         *
 * @todo What if the caller forgets to call clear before calling matchPattern again? Can we make this class more foolproof?
         */
public void clear() {
        result.clear();
}


        /**
         * Test Cases
         */
        public static void main(String[] args) {
                System.out.println("Once upon a problem…");


                PatternMatcher pm = new PatternMatcher();
                List<String> result;
String expr;
int testCaseNbr = 1;


// @todo Make a method to execute each test case
// Test Case
expr = "1?00";
System.out.println("\nTest Case Nbr<"+testCaseNbr+">, Expression<"+expr+">");
result = pm.matchPattern(expr);
System.out.println(result);
result.clear();
testCaseNbr++;


// 2nd Test Case
expr = "1?00?101";
System.out.println("\nTest Case Nbr<"+testCaseNbr+">, Expression<"+expr+">");
result = pm.matchPattern(expr);
System.out.println(result);
result.clear();
testCaseNbr++;




// 3rd Test Case
expr = "?";
System.out.println("\nTest Case Nbr<"+testCaseNbr+">, Expression<"+expr+">");
result = pm.matchPattern(expr);
System.out.println(result);
result.clear();
                testCaseNbr++;


// 4th Test Case
expr = "1";
System.out.println("\nTest Case Nbr<"+testCaseNbr+">, Expression<"+expr+">");
result = pm.matchPattern(expr);
System.out.println(result);
result.clear();
testCaseNbr++;
                
// 5th Test Case
expr = "???";
System.out.println("\nTest Case Nbr<"+testCaseNbr+">, Expression<"+expr+">");
result = pm.matchPattern(expr);
System.out.println(result);
result.clear();
testCaseNbr++;
}
}


/* A walk through the call tree:
matchPattern(1?00)
matchPattern(1000)
        result = [1000]
matchPattern(1100)
*/

/* A walk through the call tree:
matchPattern(1?00?101)
matchPattern(1000?101)
matchPattern(10000101)
        result = [10000101]
                matchPattern(10001101)
                        result = [10000101,10001101]
matchPattern(1100?101)
matchPattern(11000101)
                        result = [10000101,10001101,11000101]
matchPattern(11001101)
                        result = [10000101,10001101,11000101,11001101]


*/