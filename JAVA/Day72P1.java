/*
Aziz is given a set of integers SOI[], consists of both +ve and -ve integers.

Aziz wants to find the maximum sum of contiguous subset of the SOI, with a twist.

You have to perform the following operation before finding the maximum sum:
- You are allowed to replace exactly one integer X, with its square value (X*X).

Can you please help Aziz to find out the maximum sum of contiguous subset.


Input Format:
-------------
Single line of space separated integers, values of the array.

Output Format:
--------------
Print an integer value as result.


Sample Input-1:
---------------
-5 -3 1 2 -3 4 -4 3

Sample Output-1:
----------------
26

Explanation:
------------
max sum is: (-5)^2 + (-3) + 1 + 2 + (-3) + 4 = 26


Sample Input-2:
---------------
2 -2 2 2 -2 -2 2

Sample Output-2:
----------------
10

Explanation:
------------
max sum is: 2 +(-2)^2 + 2 + 2 = 10 
*/

import java.util.*;
public class Day72P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        int n = s.length;
        int[] ar = new int[n];
        
        for(int i=0;i<n;i++) ar[i] = Integer.parseInt(s[i]);
        
        int a = ar[0];
        int b = ar[0]*ar[0];
        int res = Math.max(a, b);
        
        for(int i=1;i<n;i++){
            int newA = Math.max(a + ar[i], ar[i]);
            int newB = Math.max(b+ar[i], Math.max(a+ar[i]*ar[i], ar[i]*ar[i]));
            
            a = newA;
            b = newB;
            
            res = Math.max(res, Math.max(a, b));
        }
        
        System.out.println(res);
        
        // int pre[] = new int[n];
        // int suf[] = new int[n];
        
        // pre[0] = ar[0];
        // suf[n-1]=ar[n-1];
        // for(int i=1;i<n;i++){
        //     pre[i] = Math.max(ar[i], pre[i-1]+ar[i]);
        // }
        
        // for(int i=n-2;i>=0;i--){
        //     suf[i] = Math.max(ar[i],suf[i+1]+ar[i]);
        // }
        
        // int res = Integer.MIN_VALUE;
        // for(int i=0;i<n;i++){
        //   int tot = ar[i]*ar[i];
        //   if(i>0) tot+=pre[i-1];
        //   if(i<n-1) tot+=suf[i+1];
        //   res = Math.max(res, tot);
        // }
        
        // System.out.println(res);
    }
}