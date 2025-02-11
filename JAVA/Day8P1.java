/*
Mr. Rakesh is interested in working with Data Structures.

He has constructed a Binary Tree (BT) and asked his friend
Anil to check whether the BT is a self-mirror tree or not.

Can you help Anil determine whether the given BT is a self-mirror tree?
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
import java.util.stream.*;

class Tree{
  int val;
  Tree left;
  Tree right;

  Tree(int val){
    this.val = val;
    this.left = this.right = null;
  }
}

public class Day8P1{
  public static void main(String... args){
    Scanner sc = new Scanner(System.in);
    String[] nodes = sc.nextLine().split(" ");
    List<Integer> list = Arrays.stream(nodes).map(Integer :: parseInt).collect(Collectors.toList());

    Tree tree = construct(list, 0);

    System.out.println(isMirror(tree, tree));
    sc.close();
  }

  public static Tree construct(List<Integer> list, int pos){
    if(list.size() == 0 || pos>=list.size()) return null;
    if(list.get(pos) == -1) return null;
    Tree root = new Tree(list.get(pos));
    root.left = construct(list, 2*pos+1);
    root.right = construct(list, 2*pos+2);
    return root;
  }

  public static boolean isMirror(Tree left, Tree right){
    if(left==null && right==null) return true;
    if(left==null || right == null) return false;
    if(left.val != right.val) return false;
    return isMirror(left.left, right.right) && isMirror(left.right, right.left);
  }
}