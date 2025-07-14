/*
A wall clock is a complete whole circle and cover 360°.
You are given the time as HH:MM.
Your task is to return the angle between the Hours hand and Minutes hand
of the clock and the angle should not be reflex angle.

Input Format:
-------------
A string time, HH:MM

Output Format:
--------------
Print a double result, within 10^-5 of the original value.


Sample Input-1:
---------------
04:30

Sample Output-1:
----------------
45.00000


Sample Input-2:
---------------
06:15

Sample Output-2:
----------------
97.50000
*/

import java.util.*;
public class Day79P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String t[] = sc.nextLine().split(":");
        
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        
        if(h>=12) h-=12;
        double ha = 30*h + 0.5*m;
        double ma = 6*m;
        
        double ang = Math.abs(ha - ma);
        System.out.println(String.format("%.5f", Math.min(ang, 360-ang)));
    }
}