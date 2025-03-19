/*
Mr. Rakesh is interested in working with Data Structures.

He has constructed a Binary Tree (BT) and asked his friend 
Anil to check whether the BT is a self-mirror tree or not.

Can you help Rakesh determine whether the given BT is a self-mirror tree?
Return true if it is a self-mirror tree; otherwise, return false.

Note:
------
In the tree, '-1' indicates an empty (null) node.

Input Format:
-------------
A single line of space separated integers, values at the treenode

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
2 1 1 2 3 3 2

Sample Output-1:
----------------
true


Sample Input-2:
---------------
2 1 1 -1 3 -1 3

Sample Output-2:
----------------
false
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

public class Day10P1{
    static Node build_tree(int[] nodes){
        if(nodes.length==0) return null;
        if(nodes[0] == -1) return null;
        Node root = new Node(nodes[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        
        int i=1;
        
        while(i<nodes.length){
            Node temp = q.poll();
            
            if(i < nodes.length && nodes[i]!=-1){
                temp.left = new Node(nodes[i]);
                q.offer(temp.left);
            }
            i++;
            
            if(i < nodes.length && nodes[i]!=-1){
                temp.right = new Node(nodes[i]);
                q.offer(temp.right);
            }
            i++;
        }
        return root;
    }
    
    static boolean checkMirror(Node left, Node right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return checkMirror(left.left, right.right) && checkMirror(left.right, right.left);
    }
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] nodes = new int[input.length];
        for(int i=0;i<input.length;i++) nodes[i] = Integer.parseInt(input[i]);
        
        Node root = build_tree(nodes);
        
        System.out.println(checkMirror(root.left, root.right));
    }
}