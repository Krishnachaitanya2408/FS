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
    
    public static boolean checkHeight(Node root, int ele, List<Integer> l1) {
        if (root == null) return false;
    
        l1.add(root.val);
    
        if (root.val == ele) return true;
    
        if (checkHeight(root.left, ele, l1) || checkHeight(root.right, ele, l1)) 
            return true;
    
        l1.remove(l1.size() - 1); 
        return false;
    }
    
    
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int[] nodes = new int[input.length];
        
        for(int i=0;i<nodes.length;i++) nodes[i] = Integer.parseInt(input[i]);
        
        Node root = build_tree(nodes);

        List<Integer> l1 = new ArrayList<>();
        checkHeight(root, n1, l1);
 
        List<Integer> l2 = new ArrayList<>();
        checkHeight(root, n2, l2);

        int LCA = 0;
        for(int i=0;i<Math.min(l1.size(), l2.size());i++){
            if(l1.get(i)==l2.get(i)) LCA = l1.get(i);
            else break;
        }

        List<Integer> l3 = new ArrayList<>();
        checkHeight(root, LCA, l3);

        System.out.println(l1.size()+l2.size()-2*l3.size());
    }
}