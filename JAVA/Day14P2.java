/*
Ram and Bheem are using a Desktop Computer.One day they found that keyboard 
is defective in which if you type backspace button,it will print '$', 
instead of removing one previous character.

Bheem and Ram have tried to type one word each on the same keyboard.
Return true, if both tried to type the same word. Otherwise return false.

Note:backspace for an empty text will continue empty.

Input Format:
-------------
Line-1:Two space seperated strings represents words w1,w2.

 
Output Format:
--------------
Print a boolean result.
 
Constraints:

    1 <= w1.length, w2.length <= 200
    w1 and w2 only contain lowercase letters and '$' characters.


 
Sample Input-1:
---------------
pq$r  pt$r

Sample Output-1:
----------------
true

Explanation:
------------
Both wants to type 'pr'

Sample Input-2:
---------------
se$$at cea$$t

Sample Output-2:
----------------
false

Sample Input-3:
---------------
s$$at ce$$at

Sample Output-2:
----------------
true

Explanation:
------------
Both wants to type 'at'.
*/

import java.util.*;
public class Day14P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        
        char[] w1 = words[0].toCharArray();
        char[] w2 = words[1].toCharArray();
        
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        
        for(char c : w1){
            if(c == '$' && !stack1.isEmpty())
                stack1.pop();
            else if(c!='$') stack1.push(c);
        }
        
        for(char c : w2){
            if(c == '$' && !stack2.isEmpty())
                stack2.pop();
            else if(c!='$') stack2.push(c);
        }
        
        System.out.println(stack1.equals(stack2));
    }
}