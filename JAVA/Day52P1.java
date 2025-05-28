/*
You are organizing a grand parade where 'N' marching bands will move in a
straight line. Each band must wear uniforms of exactly one color chosen from 'K'
available colors. To keep the parade visually appealing and avoid monotony, you
must follow this important guideline:

- No more than 'two consecutive bands' can wear 'uniforms of the same color'.

Given the total number of bands N and the number of uniform color choices K,
determine the total number of valid ways you can assign uniform colors to all 
bands so that the above rule is not violated.

Input Format:
-------------
Two integers N and K.

Output Format:
--------------
Print an integer, Number of ways.

Example 1:  
----------
Input: 
3 2
Output:
6  

Explanation:
------------
Bands	band-1	band-2	band-3
----- 	----- 	----- 	-----
1		c1 		c1		c2
2		c1 		c2 		c1
3		c1 		c2 		c2
4		c2 		c1 		c1
5		c2 		c1 		c2
6		c2 		c2 		c1

Example 2:  
----------
Input: 
1 1
Output: 
1


Constraints:  
- 1 <= n <= 50  
- 1 <= k <= 10^5 
- The result will always be within the range of a 32-bit signed integer.

*/

import java.util.*;

public class Day52P1{

    static int N, K;
    static Map<String, Long> memo = new HashMap<>();

    public static long countWays(int pos, int lastColor, int streak) {
        if (pos == N) return 1;

        String key = pos + "," + lastColor + "," + streak;
        if (memo.containsKey(key)) return memo.get(key);

        long total = 0;

        for (int color = 1; color <= K; color++) {
            if (color == lastColor) {
                if (streak < 2) {
                    total += countWays(pos + 1, color, streak + 1);
                }
            } else {
                total += countWays(pos + 1, color, 1);
            }
        }

        memo.put(key, total);
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        long totalWays = 0;

        for (int color = 1; color <= K; color++) {
            totalWays += countWays(1, color, 1);
        }

        System.out.println(totalWays);
    }
}
