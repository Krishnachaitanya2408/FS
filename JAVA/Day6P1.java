/*
 In a distant galaxy, an ancient civilization built a hierarchical communication 
network of interconnected relay stations. The structure of this network can be 
reconstructed using two ancient data logs:
    - Beacon Activation Order (analogous to in-order traversal)
    - Final Signal Sent Order (analogous to post-order traversal)
    
Using these logs, we can reconstruct the original relay network and process q
ueries about signals reaching specific hierarchical levels.

Given the Beacon Activation Order and the Final Signal Sent Order of a galactic 
communication network, reconstruct the relay network. After reconstructing 
the hierarchy, and the output should list the relay stations in the order they 
appear in a level-wise transmission sequence.

Input Format:
-------------
- An integer N representing the number of relay stations in the network.
- A space-separated list of N integers representing the Beacon Activation Order 
    (similar to in-order traversal).
- A space-separated list of N integers representing the Final Signal Sent Order 
    (similar to post-order traversal).

Output Format:
--------------
A list of integers, level-wise transmission sequence.


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

 class TreeNode {
     int val;
     TreeNode left, right;
 
     TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
 }
 
 public class Day6P1 {
     private static Map<Integer, Integer> inorderIndexMap;
     private static int postIndex;
     static List<Integer> ans = new ArrayList<>();
 
     public static TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd) {
         if (inStart > inEnd) return null;
 
         int rootVal = postorder[postIndex--];
         TreeNode root = new TreeNode(rootVal);
 
         int rootIndex = inorderIndexMap.get(rootVal);
 
         root.right = buildTree(inorder, postorder, rootIndex + 1, inEnd);
         root.left = buildTree(inorder, postorder, inStart, rootIndex - 1);
 
         return root;
     }
 
     private static void printLevelOrder(TreeNode root) {
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
         int postorder[] = new int[n];
 
         for (int i = 0; i < n; i++) {
             inorder[i] = sc.nextInt();
         }
         for (int i = 0; i < n; i++) {
             postorder[i] = sc.nextInt();
         }
 
        
         inorderIndexMap = new HashMap<>();
         for (int i = 0; i < inorder.length; i++) {
             inorderIndexMap.put(inorder[i], i);
         }
         postIndex = n - 1;
 
         TreeNode root = buildTree(inorder, postorder, 0, n - 1);
         printLevelOrder(root);
 
       
         System.out.println(ans);
     }
 }