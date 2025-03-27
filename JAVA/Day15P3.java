/*
There are some pages in a website, each page links with atmost two other pages.
Each page displays a number on it. The complete website is given as binary tree 
using the level order insertion technique.

You need to return the number of pages where the number in the page is equal to 
the sum of the numbers of its descendants. A descendant refers to any page that 
is linked but lower down the tree stucture of the website, no matter how many 
levels lower.

Input Format:
-------------
Single line of comma separated integers, numbers displayed in web-pages as Tree.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
11 3 5 2 1

Sample Output-1:
----------------
2


Sample Input-2:
---------------
3 2 1 0 0

Sample Output-2:
----------------
3

Explanation:
------------
For the pages diplaying the number 0: The sum of descendants is 0,
since they have no descendant pages.

*/

import java.util.*;
Class Tree{
    int val;
    Tree left, right;
    Tree(int val){
        this.val = val;
        this.left = this.right = null;
    }
}
public class Day15P3{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine.split(",");
        int[] nodes = new int[input.length];
        for(int i=0;i<input.length;i++) nodes[i] = Integer.parseInt(input[i]);
        Tree root = buildTree(nodes);
        int count = 0;
    }
    
    public static Tree buildTree(int[] nodes){
        if(nodes.length==0) return null;
        Queue<Tree> q = new LinkedList<>();
        int i=0;
        Tree root = new Tree(nodes[0])
        q.add(root);
        while(i<nodes.length){
            Tree node = q.poll();
            if(i<nodes.length && nodes[i]=-1){
                node.left = new Tree(nodes[i]);
                q.add(node.left);
            }
            if(i<nodes.length && nodes[i]=-1){
                node.right = new Tree(nodes[i]);
                q.add(node.right);
            }
        }
    }
    
    public static int find(Tree root, int sum, int count){
        if(root==null) return 0;
        if(sum == root.val || root.val == 0) count++;
        sum += find(root.left, sum, count) + find(root.right, sum, count);
    }
    
}