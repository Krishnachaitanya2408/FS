/* 
Akhila is given a string S, Where S conatins only [aeiou] letters.
A special subsequence defined as a sequence of letters derived from S
that contains all five vowels in order. It means a specail subsequence 
will have one or more a's followed by the one or more e's followed by 
one or more i's followed by one or more o's followed by one or more u's.

Your task is to help Akhila to return the length of the longest special 
subsequence in S.

Input Format:
-------------
Your code should be read input from the given attached file as a string (no line breaks)

Output Format:
--------------
An integer, the length of longest subsequence.

Sample input-1:
---------------
aeeiooua

Sample Output-1:
----------------
7

Explanation:
------------
Given S = "aeeiooua", then "aeiou" and "aeeioou" are special subsequences
but "aeio" and "aeeioua" are not


Sample input-2:
---------------
aeiaaioooaauuuaeiou

Sample Output-2:
----------------
10
*/


import java.util.Scanner;

public class Day79P4{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int[] dp = new int[5];

        for (char ch : s.toCharArray()) {
            if (ch == 'a') {
                dp[0]++;
            } else if (ch == 'e') {
                if (dp[0] > 0) {
                    dp[1] = Math.max(dp[1] + 1, dp[0] + 1);
                }
            } else if (ch == 'i') {
                if (dp[1] > 0) {
                    dp[2] = Math.max(dp[2] + 1, dp[1] + 1);
                }
            } else if (ch == 'o') {
                if (dp[2] > 0) {
                    dp[3] = Math.max(dp[3] + 1, dp[2] + 1);
                }
            } else if (ch == 'u') {
                if (dp[3] > 0) {
                    dp[4] = Math.max(dp[4] + 1, dp[3] + 1);
                }
            }
        }

        System.out.println(dp[4]);  
    }
}
