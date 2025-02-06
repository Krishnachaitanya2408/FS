/*
 *In a distant future, humanity has begun interstellar colonization, establishing 
zones of habitation and control on a new planet. Scientists have recorded two 
types of data regarding how these zones were structured:

1. Survey Order (analogous to pre-order traversal) – This record details how 
the colonization started, with the first zone established and then expanding 
into new zones following a systematic approach.
2. Planetary Layout (analogous to in-order traversal) – This document shows 
how zones were positioned relative to each other on the map, based on 
territorial boundaries.

Using this information, scientists need to reconstruct the colonization hierarchy 
(binary tree of zones) and display them level wise.

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

import java.util.*;

 class TreeNode {
     int val;
     TreeNode left, right;
 
     TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
 }
 
 public class Day6P5 {
     private static Map<Integer, Integer> inorderIndexMap;
     private static int preIndex;
     static List<Integer> ans = new ArrayList<>();
 
     public static TreeNode buildTree(int[] inorder, int[] preorder, int inStart, int inEnd) {
         if (inStart > inEnd) return null;
 
         int rootVal = preorder[preIndex++];
         TreeNode root = new TreeNode(rootVal);
 
         int rootIndex = inorderIndexMap.get(rootVal);
 
         root.left = buildTree(inorder, preorder, inStart, rootIndex-1);
         root.right = buildTree(inorder, preorder, rootIndex+1, inEnd);
 
         return root;
     }
 
     private static void printOrder(TreeNode root) {
         if (root == null) return;
 
         Queue<TreeNode> queue = new LinkedList<>();
         queue.add(root);
 
         while (!queue.isEmpty()) {
             TreeNode node = queue.poll();
             ans.add(node.val);
 
             if (node.left != null) queue.add(node.left);
             if (node.right != null) queue.add(node.right);
         }
     }
 
     public static void main(String... args) {
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int inorder[] = new int[n];
         int preorder[] = new int[n];
 
         for (int i = 0; i < n; i++) {
             inorder[i] = sc.nextInt();
         }
         for (int i = 0; i < n; i++) {
             preorder[i] = sc.nextInt();
         }

        
         inorderIndexMap = new HashMap<>();
         for (int i = 0; i < inorder.length; i++) {
             inorderIndexMap.put(inorder[i], i);
         }
         preIndex = 0;
 
         TreeNode root = buildTree(inorder, preorder, 0, n - 1);
         printOrder(root);
 
       
         System.out.println(ans);
     }
 }