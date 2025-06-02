/*
You are controlling a battlefield represented by an m x n grid. 
Each cell on this grid can be one of the following:

- A reinforced barrier ('B'), blocking your laser.
- An enemy drone ('D'), your target.
- An open cell ('0'), where you can position your robot to fire.

When your robot fires its powerful laser from an open cell, 
the beam destroys all enemy drones in the same row and column 
until the beam hits a barrier ('B'). The barrier completely stops 
the laser, protecting anything behind it.

Your goal is to identify the best position (open cell) to place 
your robot so that firing the laser destroys the maximum number 
of enemy drones in a single shot. Return this maximum number.

Input format:
-------------
Line 1: Two space separated integers, represents m & n
Next M lines: each row of battlefield


Output format:
--------------
An integer, maximum number of enemy drones destroyed in a single shot.

Example 1:
----------
input=
3 4
0 D 0 0
D 0 B D
0 D 0 0

Output=
3

Explanation: placing robot at battlefield[1][1] destroys 3 enemy drones in a
single shot.

Example 2:
----------
3 3
B B B
0 0 0
D D D

Output=
1


Constraints:
- 1 <= m, n <= 500
- battlefield[i][j] is either 'B', 'D', or '0'.

*/

import java.util.*;
public class Day55P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        
        char grid[][] = new char[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j] = sc.next().charAt(0);
            }
        }
        
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '0'){
                    res = Math.max(res ,find(i, j, grid));
                }
            }
        }
        
        System.out.println(res);
    }
    
    public static int find(int r, int c, char[][] grid){
        int count = 0;
        for(int i=c+1;i<grid[0].length;i++){
            if(grid[r][i]=='B') break;
            if(grid[r][i]=='D') count++;
        }
        
        for(int i=c-1;i>=0;i--){
            if(grid[r][i]=='B') break;
            if(grid[r][i]=='D') count++;
        }
        
        for(int i=r+1;i<grid.length;i++){
            if(grid[i][c]=='B') break;
            if(grid[i][c]=='D') count++;
        }
        
        for(int i=r-1;i>=0;i--){
            if(grid[i][c]=='B') break;
            if(grid[i][c]=='D') count++;
        }
        
        return count;
    }
    
}