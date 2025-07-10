/*
Given two strings S1 and S2, find if S2 can match S1 or not.

A match that is both one-to-one (an injection) and onto (a surjection), 
i.e. a function which relates each letter in string S1 to a separate and 
distinct non-empty substring in S2, where each non-empty substring in S2
also has a corresponding letter in S1.

Return true,if S2 can match S1.
Otherwise false.

Input Format:
-------------
Line-1 -> Two strings S1 and S2

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
abab kmitngitkmitngit

Sample Output-1:
----------------
true


Sample Input-2:
---------------
aaaa kmjckmjckmjckmjc

Sample Output-2:
----------------
true

Sample Input-3:
---------------
mmnn pqrxyzpqrxyz

Sample Output-3:
----------------
false 
*/

import java.util.*;
public class Day74P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Map<Character, String> map = new HashMap<>();
        
        System.out.println(backTrack(0,0,s[0], s[1], map));
    }
    
    public static boolean backTrack(int i, int j, String s1, String s2, Map<Character, String> map){
        if(i==s1.length() && j==s2.length()) return true;
        if(i>=s1.length() || j>=s2.length()) return false;
        
        if(map.containsKey(s1.charAt(i))){
            String v = map.get(s1.charAt(i));
            if(j + v.length() <= s2.length() && v.equals(s2.substring(j, j+v.length()))){
                return backTrack(i+1, j+v.length(), s1, s2, map);
            }
            return false;
        }

        for(int k=j+1;k<=s2.length();k++){
            map.put(s1.charAt(i), s2.substring(j, k));
            if(backTrack(i+1, k, s1, s2, map)){
                return true;
            }
        }
        
        map.remove(s1.charAt(i));
        return false;
    }
}