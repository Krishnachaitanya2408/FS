/*
Alex and his twin brother Jordan often create secret messages. One day, Jordan 
gives Alex two encrypted messages and challenges him to find the longest common 
palindromic pattern hidden within both messages.

Alex wants your help to decode the longest common palindromic subsequence that 
exists in both strings.

Your task is to determine the length of the longest subsequence that:
- Appears in both messages
- Is a palindrome

Input Format:
-------------
input1: A string representing the first encrypted message.
input2: A string representing the second encrypted message.

Output Fromat:
--------------
Return an integer representing the length of the longest common palindromic 
subsequence shared by both messages.


Sample Input: 
-------------
adfa
aagh

Sample Output:
--------------
2


Sample Input-2:
---------------
abcda
fxaaba

Sample Output:
--------------
3

Explanation:
------------
The longest palindromic subsequence common to both is "aba" with length 3.

 
*/


import java.util.*;

public class Day61P1 {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        String lcs = getLCS(s1, s2);
        int lpsLength = getLPSLength(lcs);
        System.out.println(lpsLength);
    }

    private static String getLCS(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return sb.reverse().toString();
    }

    private static int getLPSLength(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + ((i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return n == 0 ? 0 : dp[0][n - 1];
    }
}
