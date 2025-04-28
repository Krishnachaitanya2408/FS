/*
Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.
*/

import java.util.*;

public class Day11P3_Trees {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = ArrayUtils.parseIntArray(sc.nextLine());
        int s = sc.nextInt();
        int d = sc.nextInt();

        TreeNode root = BuildTree.build(arr);
        int htS = BuildTree.height(root, s);
        int htD = BuildTree.height(root, d);
        int lca = findLca(root, s, d);
        int htLca = BuildTree.height(root, lca);

        int dist = (htS+htD)-(2*htLca);
        System.out.println("Distance from source "+s+" to destination "+d+" = "+dist);
        sc.close();
    }
    public static int findLca(TreeNode root, int s, int d){
        if(root==null) return -1;
        if(root.val==s || root.val==d) return root.val;
        
        int left = findLca(root.left, s, d);
        int right = findLca(root.right, s, d);
        if(left!=-1 && right!=-1) return root.val;
        
        return (left!=-1) ? left : right;
    }
}