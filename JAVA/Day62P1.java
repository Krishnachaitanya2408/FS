/*
Luke likes to construct and play with arrays. His dad gave him an array M of 
length N and assigned him the following tasks to be done in order:
 - reate continuous groups of size i from the array M (where i goes from 1 to N).
 - For each group of size i, find the minimum value.
 - Among all the minimums from that step, select the maximum.
 - Add this selected value as the i-th element of a new array P.
 - Repeat until i = N.

Note: Use 1-based indexing for array P in logic.

Input Format:
-------------
input1: Integer N – length of array M
input2: Integer array M of length N

Output Format:
--------------
Return the array P as output.

Sample Input:
-------------
3
1 2 3

Sample Output:
--------------
3 2 1


Sample Input: 
-------------
4
20 10 30 40

Sample Output: 
--------------
40 30 10 10

 
*/

import java.util.*;

public class Day62P1 {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        int p[] = new int[n];

        for (int l = 1; l <= n; l++) {
            int[] temp = new int[n - l + 1];  // Number of groups of size l
            for (int i = 0; i <= n - l; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = i; j < i + l; j++) {
                    min = Math.min(min, a[j]);
                }
                temp[i] = min;
            }
            p[l - 1] = getMax(temp);
        }

        System.out.println(Arrays.toString(p));
    }

    public static int getMax(int[] temp) {
        int max = Integer.MIN_VALUE;
        for (int val : temp) {
            max = Math.max(max, val);
        }
        return max;
    }
}
