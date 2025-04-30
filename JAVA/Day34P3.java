/*
In a garden, there is a row of plants. The gardener need to water them regularly.
In the row of plants, some are empty places some are plants. you need to setup 
the watering kits to water the row of plants at the empty places. A watering kit
can supply water to its adjacent plants, i.e., if the watering kit is at 
i-th position it can water the plants ar 'i+1'-th and 'i-1'-th  positions.

You are given a string 'plants', consists of two characters only [P,E], where P 
indiactes plant and E indicates empty place.

Your task is to return the minimum number of watering kits needed so that 
for every plant, the gardener can supply the water to all the plants in that 
row OR -1 if it is impossible.


Input Format:
-------------
A string, consists of only two characters P and E

Output Format:
--------------
Print an integer result, the minimum num of watering kits.


Sample Input-1:
---------------
PEEEPEP

Sample Output-1:
----------------
2

Explanation: 
------------
You can setup watering kits at index-1, index-5, so all the 3 plants gets water.


Sample Input-2:
---------------
PEPEEPP

Sample Output-2:
----------------
-1

Explanation: 
------------
No empty place after the last plant in the row, so retrun -1. 
*/

import java.util.*;
public class Day34P3{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();
        
        int n = s.length;
        
        if((s[0]=='P' && s[1]=='P') || (s[n-1]=='P' && s[n-2]=='P')){
            System.out.println(-1);
                return;
        }
        
        for(int i=1;i<n-1;i++){
            if(s[i-1]=='P' && s[i]=='P' && s[i+1]=='P'){
                System.out.println(-1);
                return;
            }
        }
        
        
        int count = 0, last=0;
        for(int i=0;i<n;i++){
            if(s[i]=='P' && i != last+2){
                count++;
                last = i;
            };
        }
        System.out.println(count);
    }
}