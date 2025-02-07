/*
 
In a distant future, humanity has begun interstellar colonization, establishing 
zones of habitation and control on a new planet. Scientists have recorded two 
types of data regarding how these zones were structured:

1. Survey Order (analogous to pre-order traversal) – This record details how 
the colonization started, with the first zone established and then expanding 
into new zones following a systematic approach.
2. Planetary Layout (analogous to in-order traversal) – This document shows 
how zones were positioned relative to each other on the map, based on 
territorial boundaries.

Using this information, scientists need to reconstruct the colonization hierarchy 
(binary tree of zones) and analyze areas within a specific range of levels. 
However, due to security concerns, patrol teams will scan these zones in a 
zigzag pattern:
    - Odd levels (starting from 1) should be inspected from left to right.
    - Even levels should be inspected from right to left.

Input Format:
-------------
An integer N representing the number of zones colonized.
N space-separated integers representing the Planetary Layout Order (in-order).
N space-separated integers representing the Survey Order (pre-order).
Two space sepaarted integers,Lower Level (L), Upper Level (U)

Output Format:
--------------
Print all zone IDs within the specified levels, but in spiral order:
    - Odd levels → Left to Right.
    - Even levels → Right to Left.

Sample Input:
-------------
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7
2 3

Sample Output:
--------------
3 2 4 5 6 7

Explanation:
------------
The given Planetary Layout (in-order) and Survey Order (pre-order) correspond 
to the following colonization hierarchy:

        1
       / \
      2   3
     / \  / \
    4   5 6  7

Levels 2 to 3 in Regular Order:
Level 2 → 2 3
Level 3 → 4 5 6 7 

Spiral Order:
Level 2 (Even) → 3 2 (Right to Left)
Level 3 (Odd) → 4 5 6 7 (Left to Right)


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
 
 public class Day6P3 {
    private static int preIndex;
    
    public static TreeNode buildTree(List<Integer> inorder, List<Integer> preorder, int inStart, int inEnd) {
        if (inStart > inEnd || preIndex >= preorder.size()) return null;
        
        int rootVal = preorder.get(preIndex++);
        TreeNode root = new TreeNode(rootVal);
        
        int rootIndex = inorder.indexOf(rootVal);
        
        root.left = buildTree(inorder, preorder, inStart, rootIndex-1);
        root.right = buildTree(inorder, preorder, rootIndex+1, inEnd);
        
        return root;
    }
 
    public static void spiralOrderTraversal(TreeNode root, int lowerLevel, int upperLevel) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        boolean leftToRight = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                curLevel.add(node.val);

                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }

            if(level>=lowerLevel && level<=upperLevel){
                if(!leftToRight) Collections.reverse(curLevel);

                for(int i=0;i<curLevel.size();i++){
                    System.out.print(curLevel.get(i) + " ");
                }
            }

            leftToRight = !leftToRight;
            level++;

        }
    }
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> preorder = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
         inorder.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
         preorder.add(sc.nextInt());
        }
        int lowerLevel = sc.nextInt();
        int upperLevel = sc.nextInt();
        
        preIndex = 0;
        
        TreeNode root = buildTree(inorder, preorder, 0, n - 1);
        spiralOrderTraversal(root, lowerLevel, upperLevel);

        sc.close();
    }
 }