/*
 Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
---------------
25

Sample Output-1:
----------------
255.255.255.128


Sample Input-2:
---------------
22

Sample Output-2:
----------------
255.255.252.0

 */

import java.util.*;
public class Day4CN1 {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int CIDR = sc.nextInt();
        int mask = (int) (0xffffffff  << (32 - CIDR));
        String res = String.format("%d.%d.%d.%d", mask >> 24 & 0xff , mask >> 16 & 0xff, mask >> 8 & 0xff, mask >> 0 & 0xff);
        System.out.println(res);
    }
}