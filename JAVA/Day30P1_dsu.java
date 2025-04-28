import java.util.*;
/*
 * There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4
 */
public class Day30P1_dsu {
    static class DSU {
        int[] p;
        public DSU(int n){
            p = new int[n];
            for(int i=0;i<n;i++) p[i]=i;
        }
        public int find(int x){
            if(p[x]!=x)
                p[x] = find(p[x]);
            return p[x];
        }
        public boolean union(int x, int y){
            int rx = find(x), ry = find(y);
            if(rx!=ry){
                p[ry] = rx;
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DSU dsu = new DSU(n+1);
        int a = -1, b = -1;
        for(int i=0;i<n;i++){
            int u = sc.nextInt(), v = sc.nextInt();
            if(!dsu.union(u, v)){
                a = u;b = v;
            }
        }
        System.out.println(a+" "+b);
        sc.close();
    }
}