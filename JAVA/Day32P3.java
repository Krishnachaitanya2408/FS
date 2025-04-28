import java.util.*;
/*
 * You are given a crystal with an energy level n. Your goal is to discover all 
the different ways this crystal could have been created by combining smaller shards.

Each combination must:
- Use only shards with energy values between 2 and n - 1.
- Be represented as a list of shard values whose product equals n.
- Use any number of shards (minimum 2), and the order is ascending order.

Your task is to return all unique shard combinations that can multiply together
to recreate the original crystal.

Example 1:
---------
Input:
28

Output:
[[2, 14], [2, 2, 7], [4, 7]]

Example 2:
----------
Input:
23

Output:
[]



Constraints:
- 1 <= n <= 10^4
- Only shards with energy between 2 and n - 1 can be used.

 */
public class Day32P3 {
    static Set<List<Integer>> res;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =  sc.nextInt();
        res = new HashSet<>();
        dfs(2, n,1, new ArrayList<>());
        sc.close();
        System.out.println(res);
    }
    private static void dfs(int i, int n,int currP, List<Integer> t){
        if(currP==n){
            List<Integer> l = new ArrayList<>(t);
            Collections.sort(l);
            res.add(new ArrayList<>(l));
            return;
        }
        for(int k=2;k<n;k++){
            if(n%currP==0 && currP*k<=n){
                t.add(k);
                dfs(k, n, currP*k, t);
                t.remove(t.size()-1);
            }
        }
    }
}