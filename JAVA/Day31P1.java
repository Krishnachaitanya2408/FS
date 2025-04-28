import java.util.*;
/*
 * Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

The puzzle board has some patterns formed with boxes in it, 
the patterns may be repeated. The patterns are formed with boxes (1's) only, 
that are connected horizontally and vertically but not diagonally.

Pranav wants to find out the number of unique patterns in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of unique patterns in 
the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the grid-land.
Next M lines: contains N space-separated integers [0, 1].

Output Format:
--------------
Print an integer, the number of unique patterns in the puzzle board.


Sample Input-1:
---------------
5 5
0 1 0 1 1
1 1 1 0 1
0 1 0 1 0
1 0 1 1 1
1 1 0 1 0

Sample Output-1:
----------------
3

Explanation-1:
------------
The unique patterns are as follows:
  1			1 1	    1 
1 1 1		  1 ,	1 1
  1	   ,	
   
   
Sample Input-2:
---------------
6 6
1 1 0 0 1 1
1 0 1 1 0 1
0 1 0 1 0 0
1 1 0 0 0 1
0 0 1 0 1 1
1 1 0 1 0 0

Sample Output-2:
----------------
5

Explanation-2:
------------
The unique patterns are as follows:
1 1		1 1		    1		1 1	,	1
1   ,     1 ,	    1 1 ,		

 */
public class Day31P1{
  private static void dfs(int m, int n,int i, int j, int[][] grid, boolean[][] vis, StringBuilder sb){
    if(i>=m || j>=n || i<0 || j<0 || vis[i][j] || grid[i][j]==0) return;
    vis[i][j] = true;
    dfs(m, n, i, j+1, grid, vis, sb.append("R"));
    dfs(m, n, i+1, j, grid, vis, sb.append("D"));
    dfs(m, n, i, j-1, grid, vis, sb.append("L"));
    dfs(m, n, i-1, j, grid, vis, sb.append("U"));
    // sb.append("B");
  }
  private static int solve(int m, int n, int[][] grid){
    boolean[][] vis = new boolean[m][n];
    Set<String> set = new LinkedHashSet<>();
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(grid[i][j]==1 && !vis[i][j]){
          StringBuilder sb = new StringBuilder("S");
          dfs(m, n, i, j, grid, vis, sb);
          set.add(sb.toString());
        }
      }
    }
    System.out.println(set);
    return set.size();
  }
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(), n = sc.nextInt();
    int[][] grid = new int[m][n];
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        grid[i][j] = sc.nextInt();
      }
    }
    sc.close();
    int res = solve(m, n, grid);
    System.out.println(res);
  }
}