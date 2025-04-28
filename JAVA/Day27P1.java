/*
Ganesh has a habit of writing the words in backward order,
and writes the sentence without spaces.
 
Ganesh is given a sentence S (without spaces) and an integer C
His way of backward writing the sentence is as follows:
   - Break the sentence into 2C length words from begining to end.
   - Write the first C letters in backward direction of every 2C length word.
   - if there is a leftover word at the end of S with lenth lessthan 2C, then 
   - if length is lessthan C, write all the letters in backward direction.
   - if length is greater than or equal to C, write the first C letters
          in backward direction and keep the rest as it is.
           
 And return the sentence S after writing is completed.
 
Example of backward writing: 
Given sentence, S= "hellokmit" and C=2
Break the sentence into words: hell, okmi, t
Now apply backward writing on each word: ehll, komi, t
So, the final sentence is "ehllkomit"
 
 
Input Format:
-------------
Space separated string and integer, the word and C value
 
Output Format:
--------------
Print a string as result
 
 
Sample Input-1:
---------------
abcdefghijk 3

Sample Output-1:
----------------
cbadefihgjk
 

Sample Input-2:
---------------
appropriate 4
 
Sample Output-2:
----------------
rppaoprieta

*/

import java.util.*;
public class Day27P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        
        int count = 0, flag = 0;;
        for(int i=0;i<l;i++){
            char c = s.charAt(i);
            if(flag==0){
                sb.insert(i-count, c);
                count++;
                if(count==n) flag = 1;
            }
            else{
                sb.append(c);
                count--;
                if(count==0) flag=0;
            }
        }
        System.out.println(sb.toString());
    }
}