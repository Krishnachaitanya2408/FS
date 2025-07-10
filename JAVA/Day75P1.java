/*
Sampoornesh Babu is learning arithmatics.
His teacher given him a task to find the minimum number of operations
required to convert a given integer I to 1.

Sampoornesh is allowed to perform the following operations:
	- If I is even, convert I with I/2 .
	- In I is odd, convert I with either I+1 or I-1.

Now it's your task to help Sampoornesh Babu to find and print
the minimum number of operations required.

Input Format:
-------------
An integer I.

Output Format:
--------------
Print an integer, the minimum number of operations required.


Sample Input-1:
---------------
10

Sample Output-1:
----------------
4

Explanation:
------------
10 -> 5 -> 4-> 2 -> 1.


Sample Input-2:
---------------
15

Sample Output-2:
----------------
5

Explanation:
------------
15 -> 16 -> 8 -> 4 -> 2 -> 1.
*/

import java.util.*;
public class Day75P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        System.out.println(find(n));
    }
    
    public static int find(int n){
        if(n==1) return 0;
        
        int way1 = Integer.MAX_VALUE, way2 = Integer.MAX_VALUE, way3 = Integer.MAX_VALUE;
        if(n%2==0){
            way1 = 1 + find(n/2);
        }
        else{
            way2 = 1 + find(n+1);
            way3 = 1 + find(n-1);
        }
        return Math.min(way1, Math.min(way2, way3));
    }
}