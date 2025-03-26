/*
You have been tasked with managing scores in a unique game. 
The game begins with an empty scorecard. You are provided with a list 
of operations represented as a string of characters, denoted as operations[i], 
each corresponding to a specific action. These actions include:

A digit S: This means you should add a new score of S to the scorecard.
'A': This means you should add a new score to the scorecard, 
	which is the addition of the last two scores.
'D': This means you should add a new score to the scorecard, 
	which is double the value of the previous score.
'R': This means you should invalidate the previous score, 
	removing it from the scorecard.
	
Your objective is to calculate the sum of all the scores that remain on
the scorecard after performing all the specified operations.


Input Format:
-------------
A string consists of characters, represents series of operations.

Output Format:
--------------
An integer result.


Sample Input-1:
---------------
526RDAA

Sample Output-1:
----------------
27

Explanation:
-----------
'5' - Add 5 to the scorecard, scorecard is now [5].
'2' - Add 2 to the scorecard, scorecard is now [5, 2].
'6' - Add 6 to the scorecard, scorecard is now [5, 2, 6].
'R' - Invalidate and remove the previous score, scorecard is now [5, 2].
'D' - Add 2 * 2 = 4 to the scorecard, scorecard is now [5, 2, 4].
'A' - Add 2 + 4 = 6 to the scorecard, scorecard is now [5, 2, 4, 6].
'A' - Add 4 + 6 = 10 to the scorecard, scorecard is now [5, 2, 4, 6, 10].
The total sum is 5 + 2+ 4 + 6 + 10 = 27.


Sample Input-2:
---------------
1R

Sample Output-2:
----------------
0
*/


import java.util.*;
public class Day14P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        Stack<Integer> stack = new Stack<>();
        for(char c : s){
            if(Character.isDigit(c)){
                stack.push(Integer.parseInt(""+c));
            }
            else if(c=='R') stack.pop();
            else if(c=='D'){
                int top = stack.peek();
                stack.push(top*2);
            }
            else if(stack.size()>=2 && c=='A'){
                int fir = stack.pop();
                int sec = stack.peek();
                stack.push(fir);
                stack.push(fir+sec);
            }
        }
        
        int sum = 0;
        for(int i : stack){
            sum += i;
        }
        System.out.println(sum);
    }
}