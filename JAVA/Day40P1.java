/*
Pranav has a puzzle board filled with square boxes in the form of a grid.
Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

Pranav wants to find out the number of empty spaces which are completely 
surrounded by the square boxes (left, right, top, bottom) in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of empty groups surrounded by
the boxes in the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the board.
Next M lines: contains N space-separated either 0 or 1.

Output Format:
--------------
Print an integer, the number of empty spaces in the puzzle board.


Sample Input-1:
---------------
6 7
1 1 1 1 0 0 1
1 0 0 0 1 1 0
1 0 0 0 1 1 0
0 1 1 1 0 1 0
1 1 1 0 0 1 1
1 1 1 1 1 1 1

Sample Output-1:
----------------
2

Explanation:
------------
The 2 empty groups are as follows:
1st group starts at cell(1,1), 2nd group starts at cell(3,4).
The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
are not valid empty groups, because they are not completely surrounded by boxes.


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
1

Explanation:
------------
The only empty group starts at cell(1,1) is surrounded by boxes.
w


 
*/

import java.util.*;
public class Day40P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] grid = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                grid[i][j] = sc.nextInt();
            }
        }

        sc.close();
        int count = 0;
        int[][] vis = new int[r][c];
        for(int[] row : vis) Arrays.fill(row, -1);
        for(int i=0;i<r;i++){
            if(grid[i][0]==0 && vis[i][0]==-1){
                dfs(i, 0, grid, vis);
            }
            if(grid[i][c-1]==0 && vis[i][c-1]==-1){
                dfs(i, c-1, grid, vis);
            }
        }
        for(int j=0;j<c;j++){
            if(grid[0][j]==0 && vis[0][j]==-1){
                dfs(0, j, grid, vis);
            }
            if(grid[r-1][j]==0 && vis[r-1][j]==-1){
                dfs(r-1, j, grid, vis);
            }
        }
        for(int i=1;i<r-1;i++){
            for(int j=1;j<c-1;j++){
                if(grid[i][j]==0 && vis[i][j]==-1){
                    dfs(i, j, grid, vis);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void dfs(int x, int y, int[][] grid, int[][] vis){
        int r = grid.length;
        int c = grid[0].length;
        int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
        vis[x][y] = 1;
        for(int[] d : dir){
            int newX = x + d[0];
            int newY = y + d[1];
            if(newX >= 0 && newX < r && newY >= 0 && newY < c && grid[newX][newY]==0 && vis[newX][newY]==-1){
                dfs(newX, newY, grid, vis);
            }
        }
    }
}
