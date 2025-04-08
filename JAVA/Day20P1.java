/*
A string is called well-weighted string,if and only if
the string has equal number of 'A's and 'B's in it.

You are given a string S, divide S in to the maximum number of 
well-weighted strings. You should not leave any letter/part of the string.
Return the maximum number of well-weighted strings.

Input Format:
-------------
A string contains only A's and B's

Output Format:
--------------
Print an integer, maximum number of well-weighted strings


Sample Input-1:
---------------
ABBBBAAABA

Sample Output-1:
----------------
3

Explanation:
--------------
Well weighted strings, AB, BBBAAA, BA.


Sample Input-2:
---------------
ABAABBABAB

Sample Output-2:
----------------
4

Explanation:
--------------
Well weighted strings, AB, AABB, AB, AB.


Sample Input-3:
---------------
ABAAABBABB

Sample Output-3:
----------------
2

*/

import java.util.*;
public class Day20P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int a = 0, b=0, count=0 ;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='A') a++;
            else if(s.charAt(i)=='B') b++;
            if(a==b){
                count++;
                a=0;
                b=0;
            }
        }
        System.out.println(count);
    }
}