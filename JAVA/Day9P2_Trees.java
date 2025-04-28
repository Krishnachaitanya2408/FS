/*
Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 1 4 5 2

Sample Output-2:
----------------
[3, 4, 2]


 */

import java.util.*;
public class Day9P2_Trees {
  public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int[] vals = Arrays.stream(sc.nextLine().split(" "))
              .mapToInt(Integer::parseInt)
              .toArray();
      TreeNode root = buildTree(vals);
      List<Integer> res = balbirView(root);
      System.out.println(res);
      sc.close();
  }
  private static List<Integer> balbirView(TreeNode root){
      if(root==null) return new ArrayList<>();
      List<Integer> res = new ArrayList<>();
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()){
          int size = q.size();
          for(int i=0;i<size;i++){
              TreeNode temp = q.poll();
              if(i==size-1) res.add(temp.val);
              if(temp!=null){
                  if(temp.left!=null) q.offer(temp.left);
                  if(temp.right!=null) q.offer(temp.right);
              }
          }
      }
      return res;
  }
  private static TreeNode buildTree(int[] vals){
      if(vals.length==0 || vals[0]==-1) return null;

      Queue<TreeNode> q = new LinkedList<>();
      TreeNode root = new TreeNode(vals[0]);
      q.offer(root);

      int i = 1;
      while(i < vals.length){
          TreeNode curr = q.poll();
          if(i< vals.length && vals[i]!=-1){
              curr.left = new TreeNode(vals[i]);
              q.offer(curr.left);
          }
          i++;
          if(i<vals.length && vals[i]!=-1){
              curr.right = new TreeNode(vals[i]);
              q.offer(curr.right);
          }
          i++;
      }
      return root;
  }
}