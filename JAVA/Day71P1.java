/*
Naresh is working on expression of words.
If you give him an expression like, [p,q,r]s[t,u],
Naresh will form the words like as follows : [pst, psu, qst,qsu, rst, rsu]
Another example, [a,b]c[d,e] will be converted as: [acd, ace, bcd, bce].

Naresh will be given an expression as a string EXP, like the above format.
He needs to return all words that can be formed in like mentioned above, 
Can you help Naresh to convert iven expression into a list of words, in
lexicographical order.

NOTE: 
Expression consist of lowercase alphabets, comma, and square brackets only.

Input Format:
-------------
A string EXP, expression.

Output Format:
--------------
Print list of words, formed from the expression.


Sample Input-1:
---------------
[b]c[e,g]k

Sample Output-1:
----------------
[bcek, bcgk]


Sample Input-2:
---------------
[a,b][c,d]

Sample Output-2:
----------------
[ac, ad, bc, bd]


Sample Input-3:
---------------
[xyz]a[b,c]

Sample Output-3:
----------------
[xyzab, xyzac]

*/

import java.util.*;
public class Day71P1 {
    static List<String> res = new ArrayList<>();
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        bt(s, 0, sb);
        Collections.sort(res);
        System.out.println(res);
    }
    public static void bt(String s, int i, StringBuilder sb){
        if(i==s.length()){
            res.add(sb.toString());
            return;
        }
        
        if(s.charAt(i)=='['){
            StringBuilder token = new StringBuilder();
            List<String> list = new ArrayList<>();
            i++;
            while(i<s.length() && s.charAt(i)!=']'){
                if(s.charAt(i)==','){
                    list.add(token.toString());
                    token.setLength(0);
                }
                else{
                    token.append(s.charAt(i));
                }
                i++;
            }
            list.add(token.toString());
            int next = i+1;
            
            for(String tok : list){
                sb.append(tok);
                bt(s, next, sb);
                sb.setLength(sb.length()-tok.length());
            }
        }
        else{
            sb.append(s.charAt(i));
            bt(s, i+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}