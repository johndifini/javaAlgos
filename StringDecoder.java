import java.util.*;


/**
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string
 * inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc. Furthermore, you may assume that the original
 * data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 * 
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * @see https://leetcode.com/problems/decode-string/
 * @see https://discuss.leetcode.com/topic/57159/simple-java-solution-using-stack/3
 * @see https://discuss.leetcode.com/topic/57250/java-short-and-easy-understanding-solution-using-stack
 */
public class StringDecoder {
   public static StringBuilder decode(String s) {
      // Stack is deprecated so using double-ended Q
      Deque<Integer> multipliers = new ArrayDeque<>();
      Deque<StringBuilder> result = new ArrayDeque<>();
      result.push(new StringBuilder());
      int multiplier = 0;

      // Would be nice to use an 'enhanced' for loop, but don't want
      // the expense of converting the String to an array (ie toCharArray)
      // for (char ch : s.toCharArray()) {
      for (int i = 0; i < s.length(); i++) {
         char ch = s.charAt(i);
         if (Character.isDigit(ch)) {
            multiplier = multiplier * 10 + ch - '0';
            multipliers.push(multiplier);
         } else if (ch == '[') {
            result.push(new StringBuilder());
            multiplier = 0; //reset
         } else if (ch == ']') {
            StringBuilder str2Multiply = result.pop();
            int times = multipliers.pop();
            StringBuilder multipliedStr = new StringBuilder();
            for (int j = 0; j < times; j += 1) {
               multipliedStr.append(str2Multiply);
            }
            result.push(result.pop().append(multipliedStr));
         } else {
            result.push(result.pop().append(ch));
         }
      }

      return result.pop();
   }


   /**
    * Test Cases
    */
   public static void main(String[] args) {
      System.out.println("Once upon a problem...");
      String encoded;


      // Modified example to use a double digit multiplier 
      encoded = "3[a]12[bc]";
      System.out.println("encoded<"+encoded+"> decoded<"+decode(encoded)+">");


      encoded = "3[a2[c]]";
      System.out.println("encoded<"+encoded+"> decoded<"+decode(encoded)+">");


      encoded = "2[abc]3[cd]ef";
      System.out.println("encoded<"+encoded+"> decoded<"+decode(encoded)+">");
   }
}


/*
A Walk Thru an Example:
s = "3[a2[c]]", return "accaccacc".


INIT:  result = [""]; multiplier = 0


  ch multipliers result       str2Multiply   multipliedStr
  3  [3]
  [              ["",""]
  a              ["","a"]
  2  [3,2]
  [              ["","a",""]
  c              ["","a","c"]
1 ]  [3]         ["","a"]     "c"            "cc"
2                ["","acc"]
1 ]  []          [""]         "acc"          "accaccacc"
2                ["accaccacc"]
*/