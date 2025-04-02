/*
In an institution there is a special keyboard.
It contains only one row of 26-keys, keys[]

The order of keys will be given, You need to findout the time taken to type a word.

The rules to find the time is as follows:
Initially you will be at first key .i.e, keys[0].
To type a character, you have to move to a key having desired character, key[j].
Time taken to type the character from key at ith index to key at jth index is |i - j|.

You will be given two strings, Keys and Word W.
Your task is to find how much time it takes to type the Word W 
using the given order of keys

NOTE: You have to use only one finger to type the word.

Input Format:
-------------
Line-1: A String Keys order.
Line-2: A String word W to type.

Output Format:
--------------
An integer T, time to type the word.


Sample Input-1:
---------------
poiuytrewqasdfghjklmnbvcxz
kmit

Sample Output-1:
----------------
39


Sample Output-2:
----------------
abcdefghijklmnopqrstuvwxyz
code

Sample Output-2:
----------------
26

*/

import java.util.*;
public class Day17P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String keys = sc.next();
        String word = sc.next();
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<keys.length();i++){
            map.put(keys.charAt(i), i);
        }
        int count = 0, prev=0, cur=0;
        for(char ch : word.toCharArray()){
            prev = cur;
            cur = map.get(ch);
            count += Math.abs(prev - cur);
        }
        System.out.println(count);
    }
}