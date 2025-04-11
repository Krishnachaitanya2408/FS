/*
Rohan has a habit of writing a word in reverse as R and then checks whether R is same as Original word W. If R is not same as W, he can remove only one character from R to make it as W.
Print 'true', if Rohan makes R equals W, Otherwise print 'false'.

Input Format:
-------------
Line-1: A string represents a word.

Output Format:
--------------
return a boolean result.

Sample Input-1:
---------------
bcba

Sample Output-1:
----------------
true

Explanation:
------------
By removing charcter 'a', we will get the word in reverse is also same.

Sample Input-2:
---------------
abcd

Sample Output-2:
----------------
false

Explanation:
-------------
There is no possibility to make the reverse is also same.

*/

import java.util.*;
public class Day23P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int i=0, j=s.length()-1, count=0;
        while(i<j){
            if(count>1) break;
            if(s.charAt(i)!=s.charAt(j) && s.charAt(i) == s.charAt(j-1)){
                count++;
                j--;
            }
            else if(s.charAt(i)!=s.charAt(j) && s.charAt(i+1) == s.charAt(j)){
                count++;
                i++;
            }
            else if(s.charAt(i)!=s.charAt(j)){
                count = 2;
                break;
            }
            else{
                i++;
                j--;
            }
        }
        System.out.println(count<=1);
    }
}