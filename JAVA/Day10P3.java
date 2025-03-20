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

class Node{
    int val;
    Node left;
    Node right;
    
    Node(int val){
        this.val = val;
        this.left = this.right = null;
    }
}

public class Day10P3{
    public static Node build_tree(int[] nodes){
        if(nodes.length==0) return null;
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(nodes[0]);
        q.add(root);
        int i=1;
        while(i<nodes.length){
            Node temp = q.poll();
            if(i<nodes.length && nodes[i]!=-1){
                temp.left = new Node(nodes[i++]);
                q.add(temp.left);
            }
            
            if(i<nodes.length && nodes[i]!=-1){
                temp.right = new Node(nodes[i++]);
                q.add(temp.right);
            }
        }
        return root;
    }
    
    public static int checkHeight(Node root, int ele, int d) {
        if (root == null) return -1;
        if (root.val == ele) return d;
        int left = checkHeight(root.left, ele, d+1);
        if(left!=-1) return left;
        return checkHeight(root.right, ele, d+1);
    }
    
    public static Node findLCA(Node root, int n1, int n2){
        if(root == null || root.val == n1 || root.val == n2) return root;
        Node left = findLCA(root.left, n1, n2);
        Node right = findLCA(root.right, n1, n2);
        if(left == null) return right;
        else if(right == null) return left;
        else return root;
    }
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int[] nodes = new int[input.length];
        
        for(int i=0;i<nodes.length;i++) nodes[i] = Integer.parseInt(input[i]);
        
        Node root = build_tree(nodes);
        int d1 = checkHeight(root, n1, 0);
        int d2 = checkHeight(root, n2, 0);

        Node LCA = findLCA(root, n1, n2);
        int LCAdist = checkHeight(root, LCA.val, 0);

        System.out.println(d1+d2-2*LCAdist);
    }
}