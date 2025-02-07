/*
 In a distant galaxy, there exists an ancient space station covered in a vast 
array of digital codes. These codes are believed to hold the key to unlocking 
powerful alien technology. The interstellar explorers call these codes "prime 
sequences."

The prime-sequence beauty of the digital code is defined as the number of prime 
sequences that meet the following conditions:
    -The prime sequence has a length of k.
    -The prime sequence is a divisor of the entire digital code.
    -The prime sequence is a prime number.

Given the digital code on the space station, represented as an integer code, and
the length of the prime sequences k, return the prime-sequence beauty of the code.

Note:
-----
Leading zeros in prime sequences are allowed.
0 is not a divisor of any value.
A prime sequence is a contiguous sequence of characters in the main digital code.

Input Format:
-------------
Line-1: space separated String and integer, code and K

Output Format:
-------------
An integer, the prime-sequence beauty of the code.


Sample Input:
-------------
239246 2 

Sample Output:
--------------
1 

Explanation:
------------
The following are the prime sequences of length k:
    "23" from "239246": 23 is a divisor of 239246 and is a prime number.
    "39" from "239246": 39 is not a divisor.
    "92" from "239246": 92 is not a divisor.
    "24" from "239246": 24 is is not a divisor 239246.
    "46" from "239246": 46 is a divisor of 239246 but is not a prime number.
    Therefore, the prime-sequence beauty is 1.

Sample Input:
-------------
24224 1

Sample Output:
--------------
3

 */


 import java.util.*;
 public class Day3P2{
     public static void main(String... args){
         Scanner sc = new Scanner(System.in);
         String str = sc.next();
         int count = 0;
         int n = sc.nextInt();
         int number = Integer.parseInt(str);
         int l = 0,r=n;
         while(l<=r&&r<=str.length()){
             String s = str.substring(l,r);
             int num = Integer.parseInt(s);
             if(number%num==0&&isprime(num)) count++;
             l++;
             r++;
         }
         System.out.println(count);

         sc.close();    
     }
     public static boolean isprime(int n){
         if(n<=1) return false;
         for(int i = 2;i<=n/2;i++){
             if(n%i==0) return false;
         }
         return true;
     }
     
 }