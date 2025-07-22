/*
Sumanth has an idea to calculate the ABD value of a string.
An ABD value is defined as the absolute diffrence between
the most occurance count of a charcter and the least occurance count 
of another character in the given string.

Sumanth is given a string S,
He wants to find out, the sum of ABD values of all the substrings of S,
where ABD > 0.

Your task is to help Sumanth to find total ABD value of substrings of S.

Input Format:
-------------
A String S

Output Format:
--------------
Print an integer, sum of ABD of all the strings of S


Sample Input-1:
---------------
abbcc

Sample Output-1:
----------------
5

Explanation: 
------------
The substrings with non-zero ABD are as follows:
Substring and ABD value -> "abb"-1,"abbc"-1,"abbcc"-1,"bbc"-1,"bcc"-1
The total sum of ABD is, 5


Sample Input-2:
---------------
abcbabc

Sample Output-2:
----------------
15

Explanation: 
------------
The substrings with non-zero ABD are as follows:
substring and ABD value -> "abcb"-1,"abcba"-1,"abcbab"-2,"abcbabc"-1,"bcbabc"-2
"bcbab"-2, "bcba"-1, "bcb"-1, "cbab"-1,"cbabc"-1,"bab"-1, "babc"-1.
The total sum of ABD is, 15
*/

import java.util.*;
public class Day84P3{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        int res = 0, l=3;
        
        while(l<=s.length()){
            res += find(s, l);
            l++;
        }
        System.out.println(res);
    }
    
    public static int find(String s, int l){
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        
        int i=0, sum=0;
        while(i<s.length()){
            freq[s.charAt(i)-'a']++;
            if(i>=l-1){
                int max = getMax(freq);
                int min = getMin(freq);
                sum += max-min;
                freq[s.charAt(i-l+1)-'a']--;
            }
            i++;
        }
        return sum;
    }
    
    public static int getMax(int freq[]){
        int max = 0;
        for(int i:freq){
            max = Math.max(max, i);
        }
        return max;
    }
    
    public static int getMin(int freq[]){
        int min = Integer.MAX_VALUE;
        for(int i:freq){
            if(i!=0) min = Math.min(min, i);
        }
        return min;
    }
}
