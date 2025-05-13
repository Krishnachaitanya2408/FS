/*
Venkatadri is a maths teacher.
He is teaching matrices to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the special matrix, 
in the iven matrix A[m][n].
A special matrix has following property:
	- The sum of elements in each row, each column and the two diagonals are equal.
	- Every 1*1 matrix is called as a special matrix.
	- The size of the special matrix should be a square, i.e., P*P.

Your task is to help the students to find the speical matrix  with max size P.


Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, maximum size P of the special matrix.


Sample Input-1:
---------------
5 5
7 8 3 5 6
3 5 1 6 7
3 5 4 3 1
6 2 7 3 2
5 4 7 6 2

Sample Output-1:
----------------
3

Explanation:
------------
The special square is:
5 1 6
5 4 3
2 7 3


Sample Input-2:
---------------
4 4
7 8 3 5
3 2 1 6
3 2 3 3
6 2 3 3

Sample Output-2:
----------------
2

Explanation:
------------
The special square is:
3 3
3 3
 
*/

import java.util.*;
public class Day41P2{
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
        
        System.out.println(find(grid, m , n));
    }
    
    public static int find(int[][] grid, int m , int n){
        int maxS =  Math.min(m, n);
        
        for(int s = maxS; s >1; s--){
            for(int i=0;i<= m-s; i++){
                for(int j=0;j<= n-s;j++){
                    if(special(grid, i, j, s)) return s;
                }
            }
        }
        return 1;
    }
    
    public static boolean special(int[][] grid, int r , int c, int k){
        int tar = 0;
        
        for(int i=0;i<k;i++){
            tar += grid[r][c+i];
        }
        
        for(int i=1;i<k;i++){
            int rSum = 0;
            for(int j=0;j<k;j++){
                rSum += grid[r+i][c+j];
            }
            if(rSum!=tar) return false;
        }
        
        for(int i=0;i<k;i++){
            int cSum = 0;
            for(int j=0;j<k;j++){
                cSum += grid[r+j][c+i];
            }
            if(cSum!=tar) return false;
        }
        
        int dSum = 0;
        for(int i=0;i<k;i++){
            dSum += grid[r+i][c+i];
        }
        if(dSum != tar) return false;
        
        dSum = 0;
        for(int i=0;i<k;i++){
            dSum += grid[r+i][c+k-i-1];
        }
        
        if(dSum != tar) return false;
        return true;
    }
}