/*
In a forest, There are N redwoord trees in a row.
You are given the heights of the trees as heights[],

You are task is to find the longest tree arrangement as follows:
	- Minimum size of the tree arrangement is 3.
	- And there exist a Tree-'i' with heights[i], where 0 < i < N-1.
		- heights[0] < heights[1] < heights[2] <...< heights[i] and
		-  heights[i] > heights[i+1] > heights[i+2] >...>heights[N-1] 

And return the length of the longest tree arrangement.
If there is no such arrangement, return 0.

Input Format:
-------------
Line-1: An integer N, number of elements.
Line-2: N space separated integers, value of the elements.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
8
4 2 5 7 4 2 3 6

Sample Output-1:
----------------
5

Explanation:
------------
The longest tree arrangement is : 2 5 7 4 2


Sample Input-2:
---------------
4
2 4 5 7

Sample Output-2:
----------------
0
*/

import java.util.*;

public class Day18P2 {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n];
        for(int i=0; i<n; i++) {
            h[i] = sc.nextInt();
        }

        int count = 0;
        int lcount = 0, rcount = 0;
        int i = 0;
        boolean flag = false;

        while(i<n-1 && h[i]>=h[i+1]){
            i++;
        }

        while(i<n-1){
            if(!flag){
                if(h[i]<h[i+1]){
                    lcount++;
                }
            else if(h[i]>h[i+1] && lcount>0){
                    flag = true;
                    rcount++;
                }
            }
            else{
                if (h[i] > h[i+1]){
                    rcount++;
                }
                else{
                    if(rcount>0){
                        count = Math.max(count, lcount + rcount + 1);
                    }
                    lcount = 0;
                    rcount = 0;
                    flag = false;
                    if(h[i] < h[i+1]){
                        lcount = 1;
                    }
                }
            }
            i++;
        }

        if(flag && rcount>0){
            count = Math.max(count, lcount + rcount + 1);
        }

        System.out.println(count);
    }
}