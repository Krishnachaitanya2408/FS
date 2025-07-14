/*
Mr Sudhakar is working with Strings,
He is given a String S, He wants to sort the characters in S in descending order
based on the frequency of the characters. If two or more characters have same 
frequency then arrange them in sorted-order.

Your task is to help Mr Sudhakar to convert the string S into sorted order of 
frequency.

Note: The frequency of a character is the number of times it appears in the string.

Input Format:
----------------
A String S, conatins bothe lower case and upper case letters.

Output Format:
------------------
Print a string after conversion.


Sample Input-1:
---------------
divide

Sample Output-1:
----------------
ddiiev

Explanation: 
------------
d and e have same frequecy and i and v have same frequency.
So sorted-order is ddeevi.


Sample Input-2:
---------------
TomAto

Sample Output-2:
----------------
ooATmt


Sample Input-3:
---------------
rrrppp

Sample Output-3:
----------------
ppprrr
*/

import java.util.*;

public class Day78P2 {
  public static void main(String... args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();

    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }

    List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

    list.sort((a, b) -> {
      if (!a.getValue().equals(b.getValue())) {
        return b.getValue() - a.getValue();  // freq descending
      } else {
        return a.getKey() - b.getKey();      // char ascending
      }
    });

    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Character, Integer> entry : list) {
      char ch = entry.getKey();
      int freq = entry.getValue();
      for (int i = 0; i < freq; i++) {
        sb.append(ch);
      }
    }
    System.out.println(sb.toString());
  }
}
