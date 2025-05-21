/*
Indus Infra Ltd purchased a land of size L * W acres, for their upcoming venture.
The land is divided into rectangular plots, using fences. They have kept some 
H horizontal fences as hfences[] and V vertical fences as vfences[] on the land,
where hfence[i] is the distance from the top of the land to the i-th horizontal
fence and, vfence[j] is the distance from the top of the land to the j-th 
vertical fence. Each 1*1 cell is one acre plot.

Mr.RGV wants to purchase the biggest plot available to build a Guest-house.
Your task is to help Mr.RGV to find the biggest plot vailable after the fences 
are setup in the venture.
NOTE: The answer can be a large number, return the modulo of 10^9 + 7.

Input Format:
-------------
Line-1: 4 space separated integers, L,W,H and V
Line-2: H space separated integers, hfence[] in the range [0, L]
Line-3: V space sepaarted integers, vfence[] in the range [0, W]

Output Format:
--------------
Print an integer result, the area of biggest plot.


Sample Input-1:
---------------
5 6 2 2
2 3
2 5

Sample Output-1:
----------------
6


Sample Input-2:
---------------
5 6 1 1
3
4

Sample Output-2:
----------------
12

*/
import java.util.*;
public class Day47P2 {
    public static int mq(int L, int W, int[] hfence, int[] vfence) {
        int mod = 1_000_000_007;
        List<Integer> hl = new ArrayList<>();
        for (int h : hfence) hl.add(h);
        hl.add(0);
        hl.add(L);
        Collections.sort(hl);
        List<Integer> vList = new ArrayList<>();
        for (int v : vfence) vList.add(v);
        vList.add(0);
        vList.add(W);
        Collections.sort(vList);
        int maxH = 0, maxV = 0;
        for (int i = 1; i < hl.size(); i++) 
            maxH = Math.max(maxH, hl.get(i) - hl.get(i - 1));
        for (int i = 1; i < vList.size(); i++)
            maxV = Math.max(maxV, vList.get(i) - vList.get(i - 1));
        long area = (1L * maxH * maxV) % mod;
        return (int) area;
}
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int L = sc.nextInt();
    int W = sc.nextInt();
    int H = sc.nextInt();
    int V = sc.nextInt();
    int[] hfence = new int[H];
    for (int i = 0; i < H; i++) hfence[i] = sc.nextInt();
    int[] vfence = new int[V];
    for (int i = 0; i < V; i++) vfence[i] = sc.nextInt();
    System.out.println(mq(L, W, hfence, vfence));
    }
}


