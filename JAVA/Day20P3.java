/*
In the context of linguistic harmony, we define a "harmonious string" as a string where 
every alphabet it contains appears both in uppercase and lowercase forms. For instance, 
a string like "pqQpP" is harmonious because it has both 'P' and 'p' as well as 'Q' and 'q'. 
Conversely, a string like "pqP" is not harmonious as it fails to meet this condition, 
with 'q' present while 'Q' is absent.

Your are given a string S, your task is  to return the longest harmonious substring in S. 
If there are multiple answers meeting this criterion, you should return the one that appears 
earliest in the string. If there is no harmonious substring, you should return an empty string.

Input Format:
-------------------
A string S

Output Format:
-------------------
Prin the longest harmonious string.


Sample Input:
--------------
QcvcCcq

Sample Output:
---------------
cCc


Sample Input:
--------------
pqrs

Sample Output:
--------------
""

*/

import java.util.*;
public class Day20P3{
    static String res = new String();
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        check(s, 0, s.length()-1);
        System.out.print(res);
    }
    
    public static void check(String s, int start, int end){
        if(start>end) return;
        String cur = s.substring(start, end+1);
        if(isValid(cur) && res.length() < cur.length())
            res = cur;
            
        int pos = -1;
        for(int i=start;i<=end;i++){
            char ch = s.charAt(i);
            char opp = Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
            if(cur.indexOf(opp)==-1){
                pos = i;
                break;
            }
        }
        
        if(pos==-1) return;
        check(s, start, pos - 1);
        check(s, pos+1, end);
    }
    
    public static boolean isValid(String s){
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray()){
            set.add(c);
        }
        
        for(char ch :s.toCharArray()){
            char opp = Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
            if(!set.contains(opp)) return false;
        }
        return true;
    }
}