/*Pramod plans to design a program that generates all possible valid IP addresses from a given string S.
It is guaranteed that S contains only digits.

Help Pramod by designing a program that returns all valid IP addresses generated from S.
The IP addresses must be printed in lexicographic order.

Note:

- A valid IP address consists of exactly four integers, each ranging from 0 to 255, separated by single dots (.).
- IP address segments cannot contain leading zeros.
- Valid IP addresses must fall within the range 0.0.0.0 to 255.255.255.255.

Examples of invalid IP addresses: 123.012.234.255, 123.234.345.34.

Input Format:
-------------
A string S, contains only digits [0-9].

Output Format:
--------------
Print all possible IP addresses which are valid.


Sample Input-1:
---------------
23323311123

Sample Output-1:
----------------
[233.233.11.123, 233.233.111.23]


Sample Input-2:
---------------
12345678

Sample Output-2:
----------------
[1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]


Sample Input-3:
---------------
02550255

Sample Output-3:
----------------
[0.25.50.255, 0.255.0.255]*/

import java.util.*;
public class Day37P1
{
    static void backtrack(String s, int i, String curr, int parts, List<String> res)
    {
        if(parts==4 && i==s.length())
        {
            res.add(curr.substring(0, curr.length()-1));
            return;
        }
        if(parts>=4 || i>=s.length()) return;
        for(int k=1; k<=3 && i+k<=s.length(); k++)
        {
            String x=s.substring(i,i+k);
            if((x.length()>1 && x.startsWith("0")) || Integer.parseInt(x)>255)
               continue;
            backtrack(s, i+k, curr+x+".", parts+1, res);
        }
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        List<String> res=new ArrayList<>();
        backtrack(s, 0, "", 0, res);
        Collections.sort(res);
        System.out.print(res);
        sc.close();
    }
}