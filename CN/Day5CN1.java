/*
 Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
whether IP-1 and IP-2 belongs to same host range or not.

Input Format:
---------------
Two space separated strings, IP1 and IP2.
An integer, CIDR (subnet mask).

Output Format:
---------------
A boolean result.


Sample Input-1:
-----------------
192.168.1.10 192.168.1.20
24

Sample Output-1:
------------------
true


Sample Input-2:
-----------------
192.0.2.1 192.0.3.253
24

Sample Output-2:
------------------
false

 */

 import java.util.*;
 public class Day5CN1{
     public static void main(String... args){
         Scanner sc = new Scanner(System.in);
         String ipaddr1 = sc.next();
         String ipaddr2 = sc.next();
         int CIDR = sc.nextInt();
 
         String[] ip1 = ipaddr1.split("\\.");
         String[] ip2 = ipaddr2.split("\\.");
         int mask = (int) (0xffffffff  << (32 - CIDR));
         String[] subnet = String.format("%d.%d.%d.%d", 
                                     mask >> 24 & 0xff ,
                                     mask >> 16 & 0xff,
                                     mask >> 8 & 0xff,
                                     mask >> 0 & 0xff).split("\\.");
                                     
         String[] network1 = new String[4];
         String[] network2 = new String[4];
         
         for(int i=0;i<4;i++){
             int ipi1 = Integer.parseInt(ip1[i]);
             int ipi2 = Integer.parseInt(ip2[i]);
             int subi = Integer.parseInt(subnet[i]);
             int net1i = ipi1&subi;
             int net2i = ipi2&subi;
             network1[i] = String.valueOf(net1i);
             network2[i] = String.valueOf(net2i);
         }
         
         String n1 = String.join(".",network1);
         String n2 = String.join(".",network2);
         System.out.println(n1.compareTo(n2)==0);
     }
 }