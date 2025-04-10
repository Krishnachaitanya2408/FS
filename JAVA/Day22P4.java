/*
You can call two strings X and Y are friendly strings, 
if you can swap two letters in X, so the result is equal to Y.

The condition to swap the letters is as follows:
	Swapping letters is defined as taking two indices i and j (0-indexed) 
	such that i != j and swapping the characters at A[i] and A[j] . 
	For example, swapping at indices 0 and 2 in "abcd" results in "cbad" .

You are given two strings X and Y of lowercase letters, 
return true if X and Y are friendly strings, otherwise return false.

Input Format:
-------------
Two space separated Strings X and Y

Output Format:
--------------
Print a boolean value


Sample Input-1:
---------------
abcde bacde

Sample Output-1:
----------------
true

Sample Input-2:
---------------
abcde abcde

Sample Output-2:
----------------
false

*/

import java.util.*;
public class Day22P4{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        Set<Character> set = new HashSet<>();
        
        if(s1.length() != s2.length()){
            System.out.println(false);
            return;
        }
        
        if(s1.equals(s2)){
            for(char c: s1.toCharArray()){
                if(set.contains(c)){
                    System.out.println(true);
                    return;
                }
                set.add(c);
            }
            System.out.println(false);
            return;
        }
        int count = 0;;
        for(int i = 0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                set.add(s1.charAt(i));
                set.add(s2.charAt(i));
                count++;
            }
        }
        if(count==2 && set.size()==2) System.out.println(true);
        else System.out.println(false);
    }
}