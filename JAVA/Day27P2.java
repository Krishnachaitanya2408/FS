/*
 /*
Vihaar is working with strings. 
He is given two strings A and B, and another string T,
 where the length of A and B is same.
 
You can find the relative groups of letters from A and B,
using the following rule set:
	- Equality rule: 'p' == 'p'
 	- Symmetric rule: 'p' == 'q' is same as 'q' == 'p'
 	- Transitive rule: 'p' == 'q' and 'q' == 'r' indicates 'p' == 'r'.
 	
Vihaar has to form the relatively smallest string of T,
using the relative groups of letters.
 
For example, if A ="pqr" and B = "rst" , 
then we have 'p' == 'r', 'q' == 's', 'r' == 't' .

The relatives groups formed using above rule set are as follows: 
[p, r, t] and [q,s] and  String T ="tts", then relatively smallest string is "ppq".
 
You will be given the strings A , B and T.
Your task is to help Vihaar to find the relatively smallest string of T.
 
Input Format:
-------------
Three space separated strings, A , B and T
 
Output Format:
--------------
Print a string, relatively smallest string of T.
 
 
Sample Input-1:
---------------
kmit ngit mgit
 
Sample Output-1:
----------------
 ggit
 
Explanation: 
------------
The relative groups using A nd B are [k, n], [m, g], [i], [t] and
the relatively smallest string of T is "ggit"
 
 
Sample Input-2:
---------------
attitude progress apriori
 
Sample Output-2:
----------------
 aaogoog
 
 Explanation: 
 ------------
 The relative groups using A nd B are [a, p], [t, r, o], [i, g] and [u, e, d, s]
 the relatively smallest string of T is "aaogoog"
*/

import java.util.*;
public class Day27P2{
    static class DSU{
        int[] parent = new int[26];
        public DSU(){
            for(int i=0;i<26;i++){
                parent[i] = i;
            }
        }
        
        public int find(int x){
            if(parent[x]!=x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            
            if(rootX == rootY) return;
            
            if(rootX < rootY) parent[rootY] = rootX;
            else parent[rootX] = rootY;
        }
    }
    
    public static String decode(String s1, String s2, String c){
        DSU dsu = new DSU();
        
        for(int i=0;i<s1.length();i++){
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            dsu.union(a, b);
        }
        
        StringBuilder sb = new StringBuilder();
        for(char ch : c.toCharArray()){
            int p = dsu.find((int)(ch-'a'));
            sb.append((char)(p + 'a'));
        }
        
        return sb.toString();
    }
    
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        String c = sc.next();
        System.out.println(decode(s1, s2, c));
    }
}