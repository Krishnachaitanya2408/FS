
/*
Mr Robert is working with strings.
He selected two strings S1 and S2, may differ in length,
consists of lowercase alphabets only.

Mr Robert has to update the strings S1, S2 to meet any one of the criteria:
	- All the alphabets in S1 should be less than all the alphabets in S2.
	- All the alphabets in S2 should be less than all the alphabets in S1.
	- Both S1 and S2 should have only one distinct alphabet in it.
To Achieve, one of the criteria, you are allowed to replace any one letter in 
the string with any other lowercase alphabet.

Your task is to help Mr Robert, to find the minimum replacements to be done to 
update the strings S1 and S2 to meet one of the criteria mentioned above.


Input Format:
-------------
Two space separated strings S1 and S2.

Output Format:
--------------
Print an integer, minimum number of replacements.


Sample Input-1:
---------------
apple ball

Sample Output-1:
----------------
3

Explanation:
------------
Consider the best way to make the criteria true:
- Update S2 to "baaa" in 2 replacements, and Update S1 to "cpple" in 1 replacement
then every alphabet in S2 is less than every alphabet in S1.
        (OR)
- Update S1 to "ppppp" in 3 replacements, then every alphabet in S2 is less 
than every alphabet in S1.


Sample Input-2:
---------------
kmit kmec

Sample Output-2:
----------------
2


 
*/

import java.util.*;
public class Day48P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        
        System.out.println(find(s1, s2));
    }
    
    public static int find(String s1, String s2){
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for(char c : s1.toCharArray()) freq1[c-'a']++;
        
        for(char c : s2.toCharArray()) freq2[c-'a']++;
        
        int[] pre1 = new int[27];
        int[] pre2 = new int[27];

        for(int i=0;i<26;i++) {
            pre1[i+1] = pre1[i] + freq1[i];
            pre2[i+1] = pre2[i] + freq2[i];
        }
        
        int res = Integer.MAX_VALUE;
         
        for(int i=1;i<26;i++) {
            int changes1 = (s1.length()-pre1[i]) + pre2[i]; 
            int changes2 = (s2.length()-pre2[i]) + pre1[i]; 
            res = Math.min(res, Math.min(changes1,changes2));
        }

        for(int i=0;i<26; i++) {
            int sameCharChanges = (s1.length()-freq1[i]) + (s2.length()-freq2[i]);
            res = Math.min(res, sameCharChanges);
        }

        return res;
    }
}