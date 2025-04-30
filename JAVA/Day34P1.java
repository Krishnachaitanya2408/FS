/*
Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

I	f(I)
-------
0	""
1	"0"
2	"1"
3	"00"
4	"01"
5	"10"
6	"11"
7	"000"

You are given an integer value I, where I is positive number.
Your task is to find BBC representation of  the given number I.

Input Format:
-------------
An integer I

Output Format:
--------------
Print the BBC representation of I.


Sample Input-1:
---------------
23

Sample Output-1:
----------------
1000


Sample Input-2:
---------------
45

Sample Output-2:
----------------
01110
 
*/

import java.util.*;
public class Day34P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i = 0;
        while (i<31&&(1<<i)<=n){
            i++;
        }
        i--;  
        int num = n-(1<<i)+1; 
        StringBuilder sb = new StringBuilder();
        while (i>0){
            sb.append(num & 1);
            num >>= 1;
            i--;
        }
        System.out.println(sb.reverse().toString());
    }
}
