/*
Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7

====================================
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

public class Day7P1 {
    public static Tree construct(List<Integer> levelOrder) {
        if (levelOrder == null || levelOrder.isEmpty() || levelOrder.get(0) == -1) {
            return null;
        }

        Tree root = new Tree(levelOrder.get(0));
        Queue<Tree> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < levelOrder.size()) {
            Tree current = queue.poll();
            
            if (i < levelOrder.size()) {
                if (levelOrder.get(i) != -1) {
                    current.left = new Tree(levelOrder.get(i));
                    queue.offer(current.left);
                }
                i++;
            }
            
            if (i < levelOrder.size()) {
                if (levelOrder.get(i) != -1) {
                    current.right = new Tree(levelOrder.get(i));
                    queue.offer(current.right);
                }
                i++;
            }
        }
        return root;
    }

    public static void printOrder(Tree root){
        if(root == null) return;

        printOrder(root.left);
        System.out.print(root.val + " ");
        printOrder(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        List<Integer> levelOrder = Arrays.stream(input).map(Integer::parseInt).collect(Collectors.toList());


        Tree root = construct(levelOrder);

        printOrder(root);
        sc.close();
    }
}