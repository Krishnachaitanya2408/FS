package Coding.java;
/*
Galactic Communication Network
------------------------------
In a distant galaxy, an ancient civilization built a hierarchical communication network of interconnected relay stations. The structure of this network can be reconstructed using two ancient data logs:

Beacon Activation Order (analogous to in-order traversal)
Final Signal Sent Order (analogous to post-order traversal)
Using these logs, we can reconstruct the original relay network and process queries about signals reaching specific hierarchical levels.

Given the Beacon Activation Order and the Final Signal Sent Order of a galactic communication network, reconstruct the relay network. After reconstructing the hierarchy, and the output should list the relay stations in the order they appear in a level-wise transmission sequence.

Input Format:
-------------
An integer N representing the number of relay stations in the network.
A space-separated list of N integers representing the Beacon Activation Order (similar to in-order traversal).
A space-separated list of N integers representing the Final Signal Sent Order (similar to post-order traversal).

Output Format:
--------------
For each query, print the relay stations in order of their signal transmissions within the given depth range

Sample Input:
-------------
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
Sample Output:
---------------
[1, 2, 3, 4, 5, 6, 7]


Explanation:
The logs correspond to the following hierarchical relay network:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
The level order is : 1 2 3 4 5 6 7
*/
import java.util.*;
public class Day60P2 {
    static int postInd;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] in = new int[n];
        int[] post = new int[n];
        for(int i=0;i<n;i++){
            in[i] = sc.nextInt();
            map.put(in[i], i);
        }
        for(int i=0;i<n;i++) post[i] = sc.nextInt();
        TreeNode root = build(in, post);
        List<Integer> res = levelOrder(root);
        System.out.println(res);
    }
    private static List<Integer> levelOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode t = q.poll();
                if(t.left!=null) q.offer(t.left);
                if(t.right!=null) q.offer(t.right);
                res.add(t.val);
            }
        }
        return res;
    }
    private static TreeNode build(int[] in, int[] post){
        postInd = post.length-1;
        return buildHelper(in, post, 0, in.length-1);
    }
    private static TreeNode buildHelper(int[] in, int[] post, int start, int end){
        if(start>end) return null;
        int rootVal = post[postInd--];
        TreeNode root = new TreeNode(rootVal);
        int idx = map.get(rootVal);
        root.right = buildHelper(in, post, idx+1, end);
        root.left = buildHelper(in, post, start, idx-1);
        return root;
    }
}
