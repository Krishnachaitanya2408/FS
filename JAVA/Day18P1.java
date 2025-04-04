/*
Imagine you are a librarian organizing books on vertical shelves in a grand library. The books are currently scattered across a tree-like structure, where each book (node) has a position determined by its shelf number (column) and row number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged from left to right, just as they appear in the original scattered arrangement.

Example 1:
Input:
3 9 20 -1 -1 15 7
Output: 
[[9],[3,15],[20],[7]]

Explanation:
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]

Example 2:
Input:
3 9 8 4 0 1 7
Output: 
[[4],[9],[3,0,1],[8],[7]]

Explanation:
          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

Library Organization Rules:
1. Each column represents a shelf from left to right.
2. Books on the same shelf are arranged from top to bottom.
3. If books share the same position, they are arranged left to right in order of appearance.

*/


import java.util.*;
class Tree{
    int val;
    Tree left;
    Tree right;
    Tree(int val){
        this.val = val;
        this.left = this.right = null;
    }
}

public class Day18P1{
    public static class NodeInfo{
        Tree node;
        int pos;
        NodeInfo(Tree node, int pos){
            this.node = node;
            this.pos = pos;
        }
    }
    
    public static List<List<Integer>> getCols(Tree root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<NodeInfo> q = new LinkedList<>();
        q.add(new NodeInfo(root, 0));
        int min = 0, max = 0;
        while(!q.isEmpty()){
            NodeInfo cur = q.poll();
            Tree node = cur.node;
            int pos = cur.pos;
            
            map.putIfAbsent(pos, new ArrayList<>());
            map.get(pos).add(node.val);
            
            min = Math.min(min, pos);
            max = Math.max(max, pos);
            
            if(node.left!=null) q.add(new NodeInfo(node.left, pos-1));
            if(node.right!=null) q.add(new NodeInfo(node.right, pos+1));
        }
        
        for(int i=min;i<=max;i++){
            result.add(map.get(i));
        }
        
        return result;
    }
    
    public static Tree buildTree(int[] nodes){
        if(nodes.length==0) return null;
        Queue<Tree> q = new LinkedList<>();
        int i = 1;
        Tree root = new Tree(nodes[0]);
        q.add(root);
        while(i < nodes.length){
            Tree node = q.poll();
            if(i<nodes.length && nodes[i]!=-1){
                node.left = new Tree(nodes[i]);
                q.add(node.left);
            }
            i++;
            if(i<nodes.length && nodes[i]!=-1){
                node.right = new Tree(nodes[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        int[] nodes = new int[inp.length];
        for(int i=0;i<inp.length;i++) nodes[i] = Integer.parseInt(inp[i]);
        
        Tree root = buildTree(nodes);
        
        List<List<Integer>> list = getCols(root); 
        System.out.println(list);
    }
}