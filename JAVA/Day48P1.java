/*
Sampoornesh Babu is working with Strings.
He is trying to form a palindrome using two strings P and Q.
The rules to form the palindrome are as follows:
	- Divide the strings P and Q into two parts, and length of P and Q are same.
	- Division of P and Q should be done at same index position.
	- After Division P -> P1 + P2 and Q -> Q1 + Q2, where + indicates concatenation.
	- Now, check whether P1 + Q2 or Q1 + P2 forms a palindrome or not.
	- if palindrome is formed print 'true', otherwise 'false'. 

For Example: 'job' can be divided in the following ways:
""+"job", "j"+"ob",  "jo"+"b", "job"+"".

Your task is to help Sampoornesh Babu to find whether palindrome can be 
formed with the strings P and Q.

Input Format:
-------------
Two space separated strings P and Q

Output Format:
--------------
Print a boolean value, whether can you form a palindrome or not.


Sample Input-1:
---------------
mortal carrom

Sample Output-1:
----------------
true

Explanation:
------------
Divide P="mortal" and Q="carrom" at index 3 as follows:
    P -> "mor" + "tal", P1 = "mor" and P2 = "tal"
    Q -> "car" + "rom", Q1 = "car" and Q2 = "rom"

P1 + Q2 = "morrom" is a palindrome,so print true.


Sample Input-2:
---------------
romans carrom

Sample Output-2:
----------------
false
 
*/

import java.util.*;
public class Day48P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        
        int len = s1.length();
        
        for(int i=0;i<len;i++){
            if(check(i, len, s1, s2)){
                System.out.println(true);
                return;
            }
        }
        
        System.out.println(false);
    }
    
    public static boolean check(int ind, int len, String s1, String s2){
        String cur1 = s1.substring(0, ind+1) + s2.substring(ind+1);
        String cur2 = s2.substring(0, ind+1) + s1.substring(ind+1);
        int l=0, r=len-1;
        boolean flag1=true, flag2=true;
        while(l<r){
            if(flag1 && cur1.charAt(l)!=cur1.charAt(r)){
                flag1 = false;
            }
            
            if(flag2 && cur2.charAt(l)!=cur2.charAt(r)){
                flag2 = false;
            }
            
            if(!flag1 && !flag2) return false;
            l++;
            r--;
        }
        
        return true;
    }
}