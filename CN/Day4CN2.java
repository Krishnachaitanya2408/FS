/*
 * Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, netwrok IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

 */


import java.util.*;
public class Day4CN2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String ipaddr = sc.nextLine();
        int CIDR = sc.nextInt();

        String[] ip = ipaddr.split("\\.");
        int mask = (int) (0xffffffff  << (32 - CIDR));
        String[] subnet = String.format("%d.%d.%d.%d", 
                                    mask >> 24 & 0xff ,
                                    mask >> 16 & 0xff,
                                    mask >> 8 & 0xff,
                                    mask >> 0 & 0xff).split("\\.");
                                    
        String[] network = new String[4];
        String[] broadcast = new String[4];
        
        for(int i=0;i<4;i++){
            int ipi = Integer.parseInt(ip[i]);
            int subi = Integer.parseInt(subnet[i]);
            int neti = ipi&subi;
            network[i] = String.valueOf(neti);
            int broadi = neti | (~subi & 0xff);
            broadcast[i] = String.valueOf(broadi);
        }
        
        System.out.println(String.join(".", network) + " " + String.join(".", broadcast));
    }
}