/*
A binary word Bn is formed as follows:
    B[1] = "0"
    B[i+1] =  B[i] $ "1" $ reverse(complement(B[i])) for i > 1

where $ denotes the concatenation operation, reverse(complement(B)) returns 
the reversed word of complement(B), which perform 1's complement of B 
(0 changes to 1 and 1 changes to 0).

For example, the first 4 words in the above sequence are:

    B[1] = "0"
    B[2] = "011"
    B[3] = "0111001"
    B[4] = "011100110110001"

Return the Pth bit in B[N]. It is guaranteed that P is valid for the given N.

Input Format:
-------------
Line-1: Two space seperated integers represents N and P.

Output Format:
--------------
Return a bit (0 or 1).


Sample Input-1:
---------------
3 4

Sample Output-1:
----------------
1

Explanation:
------------
B[3] = "0111001" and 4th bit is 1.

Sample Input-2:
---------------
4 10

Sample Output-2:
----------------
1

Explanation:
-------------
B[4] = "011100110110001" and 10th bit is 1.

 
*/


import java.util.*;
public class Day46P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        
        String prev = "0";
        StringBuilder cur = new StringBuilder();
        for(int i=0;i<n;i++){
            cur = new StringBuilder();
            cur.append(prev);
            cur.append('1');
            for(int k=prev.length()-1;k>=0;k--){
                if(prev.charAt(k)=='0') cur.append('1');
                else cur.append('0');
            }
            prev = cur.toString();
        }
        
        System.out.println(cur.charAt(p-1));
    }
}