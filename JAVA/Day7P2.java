/*
 Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print list of list of integers, the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7
4 5 2 6 7 3 1

Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3

 */

 import java.util.*;
 import java.util.stream.*;
 
 class Tree {
     int val;
     Tree left;
     Tree right;
 
     Tree(int val) {
         this.val = val;
         this.left = this.right = null;
     }
 }
 
 public class Day7P2 {
 
     private static int preIndex = 0;
 
     public static Tree construct(List<Integer> pre, List<Integer> post, int postStart, int postEnd) {
         if (postStart > postEnd || preIndex >= pre.size()) return null;
         Tree tree = new Tree(pre.get(preIndex++));
         if (postStart == postEnd) return tree;
 
         int leftChild = pre.get(preIndex);
         int postIndex = post.indexOf(leftChild);
 
         tree.left = construct(pre, post, postStart, postIndex);
         tree.right = construct(pre, post, postIndex + 1, postEnd - 1);
 
         return tree;
     }
 
     public static List<List<Integer>> levelOrder(Tree root) {
         List<List<Integer>> result = new ArrayList<>();
         if (root == null) return result;
 
         Queue<Tree> queue = new LinkedList<>();
         queue.offer(root);
 
         while (!queue.isEmpty()) {
             int size = queue.size();
             List<Integer> level = new ArrayList<>();
 
             for (int i = 0; i < size; i++) {
                 Tree current = queue.poll();
                 level.add(current.val);
                 
                 if (current.left != null) queue.offer(current.left);
                 if (current.right != null) queue.offer(current.right);
             }
             result.add(level);
         }
 
         return result;
     }
 
     public static void main(String... args) {
         Scanner sc = new Scanner(System.in);
         String[] input = sc.nextLine().split(" ");
         String[] input1 = sc.nextLine().split(" ");
 
         List<Integer> pre = Arrays.stream(input).map(Integer::parseInt).collect(Collectors.toList());
         List<Integer> post = Arrays.stream(input1).map(Integer::parseInt).collect(Collectors.toList());
 
         Tree root = construct(pre, post, 0, post.size() - 1);
 
         List<List<Integer>> result = levelOrder(root);
         System.out.println(result);
     }
 }
 