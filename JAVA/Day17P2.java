/*
You are given two words W1 and W2.
You need find all the mapping of W2 in W1, and 
return all the starting indices of the mappings.

The mapping of the words w2 and w1 is as follows:
	- A shuffled word contains all the characters as original word.
	The length of the words and occurrence count of each character are same.
	- find shuffled word of W2 as a substring in W1, and 
	return the starting index of substring.


Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
abcabcabc abc
 
Sample Output-1:
----------------
[0, 1, 2, 3, 4, 5, 6]



Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9]

*/

import java.util.*;
public class Day17P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] word = sc.next().toCharArray();
        
        int[] wordfreq = new int[26];
        for(char c :  word) wordfreq[c -'a']++;
        List<Integer> res = new ArrayList<>();
        int[] strfreq = new int[26];
        int l=0;
        for(int r=0;r<str.length();r++){
            if(r < word.length - 1){
                strfreq[str.charAt(r) - 'a']++;
            }
            else{
                strfreq[str.charAt(r) - 'a']++;
                if(Arrays.equals(wordfreq , strfreq)) res.add(l);
                strfreq[str.charAt(l) - 'a']--;
                l++;
            }
        }
        System.out.println(res);
    }
}