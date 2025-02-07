/*
 You are developing an application for a garden management system where each tree 
in the garden is represented as a binary tree structure. The system needs to 
allow users to plant new trees in a systematic way, ensuring that each tree is 
filled level by level.

A gardener wants to:
 - Plant trees based on user input.
 - Ensure trees grow in a balanced way by filling nodes level by level.
 - Inspect the garden layout by performing an in-order traversal, which helps 
   analyze the natural arrangement of trees.

Your task is to implement a program that:
    - Accepts a list of N tree species (as integers).
    - Builds a binary tree using level-order insertion.
    - Displays the in-order traversal of the tree.

Input Format:
-------------
- An integer N representing the number of tree plants.
- A space-separated list of N integers representing tree species.

Output Format:
--------------
A list of integers, in-order traversal of tree.


Sample Input:
-------------
7
1 2 3 4 5 6 7

Sample Output:
--------------
4 2 5 1 6 3 7


Explanation:
------------
The tree looks like this:

        1
       / \
      2   3
     / \  / \
    4   5 6  7
The in order is : 4 2 5 1 6 3 7

*/

import java.util.*;
class Tree{
    int root;
    Tree left;
    Tree right;
    Tree(int root){
        this.root = root;
        this.left = null;
        this.right = null;
    }
}

class BuildTree{
    public Tree construct(int[] nums, int pos){
        if(pos>=nums.length) return null;
        Tree root = new Tree(nums[pos]);
        root.left = construct(nums, 2*pos);
        root.right = construct(nums, 2*pos+1);
        return root;
        
    }
}
public class Day6P4{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n+1];
        
        for(int i=1;i<=n;i++) nums[i] = sc.nextInt();
        
        BuildTree buildtree = new BuildTree();
        Tree tree = buildtree.construct(nums, 1);
        
        printInorder(tree);
        
        sc.close();
    }
    
    public static void printInorder(Tree node){
      if (node == null) return;
      printInorder(node.left);
      System.out.print(node.root + " ");
      printInorder(node.right);
    }
}