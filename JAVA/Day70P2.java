/*
BCCI wants to select the group of bowlers for an upcoming test-series, 
you want to choose the group with highest number of wickets, which is 
the sum of wickets taken by all the bowlers in that group.

However, the bowler group is not allowed to have any disputes. A dispute
exists if a younger bowler has strictly highest wickets than an older bowler. 
A dispute does not occur between bowlers of the same age.

You are given information of N bowlers as two lists, wickets and ages, 
where each wickets[i] and ages[i] represents the wickets and age of 
the i-th bowler, respectively, return the highest number of wickets 
of all possible bowler groups.


Input Format:
-------------
Line-1: An integer N, number of bowlers.
Line-2: N space separated integers, wickets[].
Line-3: N space separated integers, ages[].

Output Format:
--------------
An integer, highest number of wickets of all possible bowler groups.


Sample Input-1:
---------------
4
5 3 5 6
3 5 4 2

Sample Output-1:
----------------
10

Explanation:
------------
It is best to choose 2 bowlers of age 3 and 4. 


Sample Input-2:
---------------
5
5 3 5 6 3
2 5 4 2 1

Sample Output-2:
----------------
14

Explanation:
------------
It is best to choose 3 bowlers of age 1 and 2. 
Notice that you are allowed to choose multiple bowlers of the same age.

Sample Input-3:
---------------
5
1 3 5 10 15
1 2 3 4 5

Sample Output-3:
----------------
34

Explanation:
------------
You can choose all the bowlers.
 
*/

import java.util.*;
public class Day70P2{
    static int res = 0;
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int w[] = new int[n];
        int a[] = new int[n];
        
        for(int i=0;i<n;i++) w[i] = sc.nextInt();
        for(int i=0;i<n;i++) a[i] = sc.nextInt();
        
        int b[][] = new int[n][2];
        
        for(int i=0;i<n;i++){
            b[i][0] = a[i];
            b[i][1] = w[i];
        }
        
        Arrays.sort(b, (x, y) -> {
            if(x[0]==y[0]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });
        
        backtrack(b, 0, 0, -1, -1);
        System.out.println(res);
    }

    public static void backtrack(int[][] b, int i, int curSum, int lastage, int lastwick) {
        if (i==b.length){
            res = Math.max(res, curSum);
            return;
        }

        int age = b[i][0];
        int wick = b[i][1];

        if (age == -1 || (age >= lastage && wick >= lastwick)) {
            backtrack(b, i+1, curSum + wick, age, wick);
        }

        backtrack(b, i+1, curSum, lastage, lastwick);
    }
}