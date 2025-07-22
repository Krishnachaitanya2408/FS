/*
Given N satellite stations, numbered 1 to N.
These satellites are connected to send signals from one to other.
To send a signal from satellite 's' to satellite 'd', it takes 
an amount of time 't'.

You are given a list of travel times as directed edges times[i] = (s, d, t).

Your task to find the time taken to recieve signal from a satellite station K, 
to all N-1 satellite stations. If it is impossible, return -1 .

Input Format:
-------------
Line-1 ->   Three integers, N number of satellite stations, 
            K is the satellite to send signal and T is the number of edges.
Next T lines -> Three space separated integers, 's' is the source, 
            'd' is the destination, 
			't' is the time taken recieve signal from 's' to 'd'.

Output Format:
--------------
Print an integer as your result.


Sample Input-1:
---------------
4 2 3
2 1 1
2 3 1
3 4 1

Sample Output-1:
----------------
2


Sample Input-2:
---------------
5 2 4
2 1 1
2 3 2
3 4 3
5 1 4

Sample Output-2:
----------------
-1


Sample Input-3:
---------------
5 2 4
2 1 1
2 3 2
3 4 3
1 5 6

Sample Output-3:
----------------
7
 
*/

import java.util.*;

public class Day84P2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); 
        int K = sc.nextInt(); 
        int T = sc.nextInt(); 

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for(int i = 0; i < T; i++){
            int s = sc.nextInt();
            int d = sc.nextInt();
            int t = sc.nextInt();
            graph.get(s).add(new int[]{d, t});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{K, 0});

        Map<Integer, Integer> dist = new HashMap<>();

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int time = curr[1];

            if (dist.containsKey(node)) continue;

            dist.put(node, time);

            for(int[] neighbor : graph.get(node)){
                int nextNode = neighbor[0];
                int nextTime = time + neighbor[1];
                pq.offer(new int[]{nextNode, nextTime});
            }
        }

        if(dist.size()!=N){
            System.out.println(-1);
        }
        else{
            int maxTime = 0;
            for (int t:dist.values()){
                maxTime = Math.max(maxTime, t);
            }
            System.out.println(maxTime);
        }
    }
}
