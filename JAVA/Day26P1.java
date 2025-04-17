/*
Given a integer value N, indicates number of bits in a binary number.

Your task is to design a Binary Code System, where two consecutive 
values in BCS having N bits, must have one bit difference only. 
For example refer the sample testcases.

Find and print the integer values of BCS, starting from 0.


Input Format:
-------------
A integer N, number of bits in BCS

Output Format:
--------------
Print the list of integer values, in BCS form. 


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[0, 1, 3, 2]

Explanation:
------------
00 - 0
01 - 1
11 - 3
10 - 2

Sample Input-2:
---------------
3

Sample Output-2:
----------------
[0, 1, 3, 2, 6, 7, 5, 4]

Explanation:
------------
000 - 0
001 - 1
011 - 3
010 - 2
110 - 6
111 - 7
101 - 5
100 - 4

*/

import java.util.*;
public class Day26P1{
    static List<Integer> res = new ArrayList<>();
    static Set<Integer> seen = new HashSet<>();

    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(0);
        }
        
        res.add(Integer.parseInt(sb.toString(), 2));
        seen.add(Integer.parseInt(sb.toString(), 2));

        generate(sb, n);
        System.out.println(res);
        
    }
    
    public static boolean generate(StringBuilder sb, int n){
        if(res.size() == 1 << n) return true;
        for(int i=n-1;i>=0;i--){
            sb.setCharAt(i, sb.charAt(i)=='0'?'1':'0');
            int val = Integer.parseInt(sb.toString(), 2);
            if(!seen.contains(val)){
                seen.add(val);
                res.add(val);
                if(generate(sb, n)) return true;
                
                seen.remove(val);
                res.remove(res.size()-1);
            }
            
            sb.setCharAt(i, sb.charAt(i)=='0'?'1':'0');
        }
        return false;
    }
}