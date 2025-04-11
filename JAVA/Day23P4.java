/* 
Two brothers want to play a game, 
The rules of the game are: one player gives two sorted lists of 
numerical elements and a number (sum). 
The opponent has to find the closest pair of elements 
to the given sum.
-> pair consists of elements from each list

Please help those brothers to develop a program, that takes 
two sorted lists as input and return a pair as output.

Input Format:
-------------
size of list_1
list_1 values
size of list_2
list_2 values
closest number

Output Format:
--------------
comma-separated pair

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32
Sample Output-1
---------------
1,30

Sample Input-2
---------------
3
2 4 6
4
5 7 11 13
15
sample output-2
---------------
2,13


*/

import java.util.*;
class Day23P4{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=0;i<n;i++)
        a.add(sc.nextInt());
        int m=sc.nextInt();
        ArrayList<Integer> b = new ArrayList<>();
        for(int i=0;i<m;i++)
        b.add(sc.nextInt());
        int sum=sc.nextInt();
        int count=Integer.MAX_VALUE;
        int p1=0,p2=0;
        
        int l=0,r=m-1;
        while(l<n && r>=0){
            int add=a.get(l)+b.get(r);
            int diff=Math.abs(add-sum);
            if(diff<count){
                count=diff;
                p1=a.get(l);
                p2=b.get(r);
            }
            if(add>sum)
            r--;
            else
            l++;
        }
        
        System.out.println(p1+","+p2);
        
    }
}