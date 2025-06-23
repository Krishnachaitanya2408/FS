/*
You write a love letter to your friend. However, before your friend can read it, 
someone else takes it and rotates the characters of each word from left to right 
K times.

Your task is to determine how many of the words still remain the same even after 
this rotation.

Input Format:
-------------
input1: A string containing words separated by spaces.
input2: An integer K indicating the number of right rotations for each word.

Output Format:
--------------
An integer representing the number of words that remain unchanged after K right 
rotations.


Sample Input: 
-------------
ramram santan nnnn
3

Sample Output:
--------------
2


Sample Input: 
-------------
adasda
3

Sample Output:
--------------
0

 
*/


import java.util.*;

public class Day62P2 {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        int k = sc.nextInt();
        int count = 0;

        for (String word : words) {
            int len = word.length();
            int rot = k % len;
            String rotated = word.substring(len - rot) + word.substring(0, len - rot);
            if (rotated.equals(word)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
