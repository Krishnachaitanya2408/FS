/*

You are a stealthy archaeologist exploring a circular ring of ancient tombs located deep within a jungle. Each tomb holds a certain number of precious artifacts. However, these tombs are protected by an ancient magical curse: if you disturb two adjacent tombs during the same night, the entire ring activates a trap that seals you in forever.

The tombs are arranged in a perfect circle, meaning the first tomb is adjacent to the last. You must plan your artifact retrieval carefully to maximize the number of artifacts collected in a single night without triggering the curse.

Given an integer array  artifacts  representing the number of artifacts in each tomb, return the   maximum   number of artifacts you can collect without disturbing any two adjacent tombs on the same night.

Example 1:  
Input:
2 4 3
Output:  
4   

Explanation:   You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), as they are adjacent in a circular setup.

Example 2:  
Input:
1 2 3 1
Output:  
4

Explanation:   You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without breaking the ancient rule.  
Total = 1 + 3 = 4 artifacts.

Example 3:  
Input:
1 2 3
Output:  
3 

Constraints:  
-  1 <= artifacts.length <= 100 
-  0 <= artifacts[i] <= 1000 
*/

import java.util.*;

public class Day51P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");

        int n = input.length;
        int[] a = new int[n];

        for (int i=0;i<n;i++) a[i] = Integer.parseInt(input[i]);

        int case1 = f(0, n - 1, a); 
        int case2 = f(1, n, a); 
        System.out.println(Math.max(case1, case2));
    }

    public static int f(int i, int n, int[] a){
        if (i>=n) return 0;

        return Math.max((a[i] + f(i+2, n, a)), f(i+1, n, a));
    }
}
