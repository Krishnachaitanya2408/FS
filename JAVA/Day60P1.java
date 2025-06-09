package Coding.java;
import java.util.*;
/*
In a distant future, humanity has begun interstellar colonization, establishing zones of habitation and control on a new planet. Scientists have recorded two types of data regarding how these zones were structured:

1. Survey Order (analogous to pre-order traversal) – This record details how the colonization started, with the first zone established and then expanding into new zones following a systematic approach.
2. Planetary Layout (analogous to in-order traversal) – This document shows how zones were positioned relative to each other on the map, based on territorial boundaries.

Using this information, scientists need to reconstruct the colonization hierarchy (binary tree of zones) and display them level wise.

Input Format:
--------------
An integer N representing the number of zones colonized.
A space-separated list of N integers representing the Planetary Layout Order (in-order).
A space-separated list of N integers representing the Survey Order ( pre-order ).

Output Format:
---------------
Print all zone IDs in level order:

Sample Input:
-------------
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7

Sample Output:
---------------
3 2 4 5 6 7

Explanation:
The given Planetary Layout (in-order) and Survey Order (pre-order) correspond to the following colonization hierarchy:
        1
       / \
      2   3
     / \  / \
    4   5 6  7

Level Order: [1, 2, 3, 4, 5, 6, 7]

*/
public class Day60P1 {
    static int preInd;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] in = new int[n];
        int[] pre = new int[n];
        for(int i=0;i<n;i++){
            in[i] = sc.nextInt();
            map.put(in[i], i);
        }
        for(int i=0;i<n;i++) pre[i] = sc.nextInt();
        TreeNode root = buildTree(in, pre);
        List<Integer> res = levelOrder(root);
        System.out.println(res);
    }
    private static List<Integer> levelOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode t = q.poll();
                res.add(t.val);
                if(t.left!=null) q.offer(t.left);
                if(t.right!=null) q.offer(t.right);
            }
        }
        return res;
    }
    private static TreeNode buildTree(int[] in, int[] pre){
        preInd = 0;
        return buildTreeHelper(in, pre, 0, in.length-1);
    }
    private static TreeNode buildTreeHelper(int[] in, int[] pre, int start, int end){
        if(start>end) return null;
        int rootVal = pre[preInd++];
        TreeNode root = new TreeNode(rootVal);
        int idx = map.get(rootVal);
        root.left = buildTreeHelper(in, pre, start, idx-1);
        root.right = buildTreeHelper(in, pre, idx+1, end);
        return root;
    }
}
