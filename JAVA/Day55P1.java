/* 
You are the chief designer for a futuristic garden trail, where there are n
consecutive decorative light posts along a path. Each post can be illuminated 
with one of three distinct colors: Crimson, Azure, or Emerald. The cost to 
illuminate each post with a particular color depends on the post's location and
the energy circuit required.

However, to maintain aesthetic harmony and prevent power circuit overloads, no 
two adjacent posts can have the same light color.

The cost of illuminating each post with each color is provided in a 2D array
costs, where costs[i][0] is the cost of lighting post i with Crimson, 
costs[i][1] with Azure, and costs[i][2] with Emerald.

Your goal is to find the minimum total cost to light up all posts in such a way 
that no two neighboring posts have the same color.

Input Format:
-------------
Line-1: An integer N, number of post.
Next N lines: 3 space separated integers, cost of illuminating the posts.

Output Format:
--------------
Print an integer, minimum total cost to light up all posts.


Sample Input:
---------------
3
17 2 17
16 4 5
14 3 19

Sample Output:
----------------
10

Explanation: 
------------
1st post is with Azure, 2nd post is with Emerald,
3rd post is with Crimson.
Minimum cost: 2 + 5 + 3 = 10.

*/


import java.util.*;
public class Day55P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][3];
        
        for(int i=0;i<n;i++){
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
            a[i][2] = sc.nextInt();
        }
        
        int res = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            res = Math.min(res, find(0, a, 0, n, i));
        }
        System.out.println(res);        
    }
    
    public static int find(int ind, int[][]a, int curSum, int n, int color){
        if(ind==n) return curSum;
        
        int res = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            if(i!=color){
                res = Math.min(find(ind+1, a, curSum+a[ind][i], n, i), res);
            }
        }
        return res;
    }
}
