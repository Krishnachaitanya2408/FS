/*
Gunith is interested in series and sequence problems in Maths.
Gunith is given an array A of integers, he wants to find out 
a Sequence, which is long and subsequence of given array.

Subsequence AS is the list AS[i], AS[i1], AS[i2], .... AS[ik], from the array, 
where 0 <= i< i1 < i2 < ..<ik < A.length

The sequence S has to follow this rule, S[i+1] - S[i] are all the same value 
(for 0 <= i < S.length - 1 ), and Sequence S can be formed from A

Find out the Max possible length of the Longest Sequence.

Input Format:
-------------
Line-1 -> An integer N, number of array elements
Line-2 -> N space separated integers, elements of the array.

Output Format:
--------------
Print an integer as your result.


Sample Input-1:
---------------
4
2 6 10 14

Sample Output-1:
----------------
4


Sample Input-2:
---------------
7
2 5 6 8 10 11 14

Sample Output-2:
----------------
5

Explanation:
------------
The longest possible arithmetic series is 2 5 8 11 14.

*/

import java.util.*;
public class Day68P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] a = new int[n];
        for(int i=0;i<n;i++) a[i] = sc.nextInt();
        
        System.out.println(find(a, 0, -1, -1));
    }
    
    public static int find(int[] ar, int ind, int prev, int diff){
        if(ind>=ar.length) return 0;
        int max = 0;
        
        if(prev==-1) max=1+find(ar, ind+1, ind, -1);
        else{
            int curDif = ar[ind] - ar[prev];
            if(diff==-1 || diff==curDif) max=1+find(ar, ind+1, ind, curDif);
        }
        
        int way2 = find(ar, ind+1, prev, diff);
        return Math.max(max, way2);
    }
}