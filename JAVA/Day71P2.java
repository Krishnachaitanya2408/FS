/*
A merchant has two types of idols, gold and silver.
He has arranged the idols in the form of m*n grid, 
	- the gold idols are represented as 1's 
	- the silver idols are represented as 0's.

Your task is to find the longest consecutive arrangement of gold idols, 
The arrangement can be either horizontal, vertical, diagonal or 
antidiagonal, but not the combination of these.


Input Format:
-------------
Line-1: Two space separated integers m and n, grid size.
Next m lines : n space separated integers, arrangement of idols.

Output Format:
--------------
Print an integer, longest arrangement of gold idols.


Sample Input:
------------
4 5
1 0 1 1 1
0 1 0 1 0
1 0 1 0 1
1 1 0 1 1

Sample Output:
-------------
4

Sample Input:
-------------
5 7
1 1 1 1 0 1 0
0 1 1 1 0 0 0
0 1 1 1 0 1 1
1 1 0 0 1 1 1
1 0 0 0 0 1 1

Sample Output
-------------
5

NOTE:
Solve this program using DP approach only.
 
*/

import java.util.*;
public class Day71P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        
        
        int[][][] dp = new int[m][n][4];
        
        for(int i=0;i<m;i++){
            for(int k=0;k<4;k++){
                if(grid[i][0]==1) dp[i][0][k] = 1;
            }
        }
        
        for(int j=0;j<n;j++){
            for(int k=0;k<4;k++){
                if(grid[0][j]==1) dp[0][j][k] = 1;
            }
        }
        
        for(int k=0;k<4;k++){
            if(grid[0][0]==1) dp[0][0][k] = 1;
        }
    
    
        for(int k=0;k<4;k++){
            if(grid[0][n-1]==1) dp[0][n-1][k] = 1;
        }
        
        int res = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1 && j>0) dp[i][j][0] = dp[i][j-1][0]+1;
                if(grid[i][j]==1 && i>0) dp[i][j][1] = dp[i-1][j][1]+1;
                if(grid[i][j]==1 && i>0 && j>0) dp[i][j][2] = dp[i-1][j-1][2]+1;
                if(grid[i][j]==1 && i>0 && j<n-1) dp[i][j][3] = dp[i-1][j+1][3]+1;
                   
                   
               for(int k=0;k<4;k++){
                   res = Math.max(res, dp[i][j][k]);
               }
            }
        }
        
        System.out.println(res);
        
    }
}