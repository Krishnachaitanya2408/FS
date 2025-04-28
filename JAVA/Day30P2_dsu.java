import java.util.*;
/*
 * There are N people in a private party. Initially all are strangers to each other,
and the people are identified by unique ID from 0 to N-1.

In the party, whenever two persons (person-A and person-B) become friends, they 
took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
here T-i indicates time of the photo taken, P-j person with ID 'j', and 
P-k indicates person with ID 'k'.

Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
friend of person-B", then person-A is friend of person-B.

You are given L photos information, Your task is to find the earliest time 
for which every person became friend with every other person in the party.
If there is no such earliest time, return -1.


Input Format:
-------------
Line-1: Two space separated integers, N and L.
Next L lines: Three space separated integers, log[i], 0<=i<L.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6 8
5 0 1
7 3 4
12 2 3
21 1 5
34 2 4
37 0 3
42 1 2
93 4 5

Sample Output-1:
----------------
37


Sample Input-2:
---------------
7 6
2 0 3
5 1 5
8 2 5
7 3 6
9 4 6
6 4 5

Sample Output-2:
----------------
9

 */
public class Day30P2_dsu {
    static class DSU {
        int[] p;
        public DSU(int n){
            p = new int[n];
            for(int i=0;i<n;i++) p[i] = i;
        }
        public int f(int x){
            if(p[x]!=x)
                p[x] = f(p[x]);
            return p[x];
        }
        public boolean u(int x, int y){
            int rx = f(x), ry = f(y);
            if(rx!=ry){
                p[ry] = rx;
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), e = sc.nextInt();
        List<int[]> l = new ArrayList<>();
        for(int i=0;i<e;i++){
            int t = sc.nextInt(), f1 = sc.nextInt(), f2 = sc.nextInt();
            l.add(new int[]{t, f1, f2});
        }
        sc.close();
        Collections.sort(l, (a,b)->(a[0]-b[0]));
        DSU dsu = new DSU(n);
        int i = 0;
        for(i=0;i<l.size();i++){
            int[] a = l.get(i);
            if(dsu.u(a[1], a[2])) n--;
            if(n==1){
                System.out.println(a[0]);
                return;
            }
        }
        System.out.println(-1);
    }
}