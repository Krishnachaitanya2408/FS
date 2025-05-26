/*
 
/*
Sharath is playing a game where there are N levels. Inorder to win the game he has to reach Nth level.

The rule book explains "In one step you can either cross one level or two levels".

Return the number of distinct possible ways to win the game.

Constraints:

    1 <= N <= 45

Input Format:
-------------
Line-1: An Integer N represents number of levels.
  
Output Format:
--------------
Print an integer.


Sample Input-1:
---------------
2
  
Sample Output-1:
----------------
2

Explanation:
------------
1. 1-level+ 1-level =2
2. 2 levels in one step.
   
Sample Input-2:
---------------
4
  
Sample Output-2:
----------------
5

Explanation:
------------
1. 1-level + 1-level + 1-level + 1-level = 4
2. 1-level + 1-level + 2-levels = 4
3. 1-level + 2-levels + 1-level = 4
4. 2-levels + 1-level + 1-level = 4
5. 2-levels + 2-levels  = 4 
*/

import java.util.*;
public class Day50P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(find(0, n, dp));
    }
    
    public static int find(int cur, int n, int[] dp){
        if(cur==n){
            return 1;
        }
        if(cur>n){
            return 0;
        }
        
        if(dp[cur]!=-1) return dp[cur];
        return dp[cur] = find(cur+1, n, dp)+find(cur+2, n,dp);
    } 
}