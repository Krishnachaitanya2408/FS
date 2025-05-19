/*
There are N employees from 3 different companies in a row, emps[], the employees 
are identified using company IDs as 1,2,3. A Courier Boy need to deliver 
P parcels to these 3 companies. The parcel details are given as parcels[],
where parcel[i]=[Ci,CIDi], i-th parcel, 'Ci' is Courier Boy's current position, 
and  'CIDi' is company's ID, he/she need to deliver parcel[i] from Ci position 
to the nearest employee belongs to companay ID equals to CIDi in the row.

You are given emps[] and parcels[] information,
Your task is to help the courier boy to find the distance between him to 
the nearest employee in that row to deliver the parcel.
If there is no solution found, return -1.

Input Format:
-------------
Line-1: Two space separated integers, N and P
Line-2: N space separated integers, only 1, 2 & 3.
Next P lines: Two space separated integers, position Ci and Company ID

Output Format:
--------------
Print the space separated integers, distance between boy and the employee for 
all the parcels.


Sample Input-1:
---------------
6 2
1 1 2 2 3 3
1 3
2 2

Sample Output-1:
----------------
3 0 

Sample Input-2:
---------------
5 2
1 2 3 2 1
2 1
1 1

Sample Output-2:
----------------
2 1 

 
*/

import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int emp[] = new int[n];
        for(int i=0;i<n;i++) emp[i] = sc.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
       for(int i=0;i<p;i++){
           int pos = sc.nextInt();
           int id = sc.nextInt();
           int l = pos,r=pos;
           int d = Integer.MIN_VALUE;
           boolean found=false;
           while(l>=0 || r<n){
               if(l>=0 && emp[l]==id){
                   d = pos-l;
                   found = true;
                   break;
                   
               }
               if(r<n && emp[r]==id){
                   d = r-pos;
                   found = true;
                   break;
               }
               l--;
               r++;
           }
           if(found)
           a.add(d);
           else a.add(-1);
       }
       System.out.println(a);
    }
}