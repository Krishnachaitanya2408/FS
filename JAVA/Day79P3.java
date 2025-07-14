/*
Mr Saurabh is given a list of words.
Your task is to help Mr Saurabh to find the size of largest group

A group is formed using the following rules:
- Pick one smallest word (in length) and form a group with this word
  (if it is not part of any other group yet)
- Add a letter at any place in the word without changing the relative order 
  of the letters in it, and if it forms a word which is existing in the list[],
  then add the word to the group.
- Repeat the above two steps, till all the words in the list are part of groups.

NOTE:You move form more than one group.

Input Format:
-------------
Space separated words, wordsList[].

Output Format:
--------------
Print an integer result


Sample Input-1:
---------------
many money n an mony any one mone on

Sample Output-1:
----------------
5

Explanation:
------------
the words group is : [n, on, one, mone, money]


Sample Input-2:
---------------
a ab abb babb abab baba bab abbaa

Sample Output-2:
----------------
4
*/

import java.util.*;

public class Day79P3{
    static boolean[] vis;

    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");

        Arrays.sort(s, Comparator.comparingInt(String::length));

        vis = new boolean[s.length];

        int res = 0;
        for (int i=0; i<s.length; i++) {
            res = Math.max(res, find(i, s, s[i]));
        }

        System.out.println(res);
    }

    public static int find(int index, String[] s, String cur) {
        int maxLen = 1;

        for(int i=0; i<s.length; i++) {
            if(s[i].length() == cur.length()+1 && isPredecessor(cur, s[i])) {
                maxLen = Math.max(maxLen, 1+find(i, s, s[i]));
            }
        }

        return maxLen;
    }

    public static boolean isPredecessor(String shorter, String longer) {
        if(longer.length() - shorter.length() != 1) return false;

        int i = 0, j = 0;
        boolean skipped = false;

        while(i<shorter.length() && j<longer.length()) {
            if(shorter.charAt(i) == longer.charAt(j)) {
                i++;
                j++;
            }
            else{
                if(skipped) return false;
                skipped = true;
                j++;
            }
        }

        return true;
    }
}
