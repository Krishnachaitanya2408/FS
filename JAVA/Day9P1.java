/*
VishnuVardan is working with Decision Trees for AI-based predictions.
To analyze alternative outcomes, Kishore has planned to flip the decision 
tree horizontally to simulate a reverse processing approach.

Rules for Flipping the Decision Tree:
	- The original root node becomes the new rightmost node.
	- The original left child becomes the new root node.
	- The original right child becomes the new left child.
This transformation is applied level by level recursively.

Note:
------
- Each node in the given tree has either 0 or 2 children.
- Every right node in the tree has a left sibling sharing the same parent.
- Every right node has no further children (i.e., they are leaf nodes).

Your task is to help VishnuVardan flip the Decision Tree while following 
the given transformation rules.

Input Format:
-------------
Space separated integers, nodes of the tree.

Output Format:
--------------
Print the list of nodes of flipped tree as described below.


Sample Input-1:
---------------
4 2 3 5 1

Sample Output-1:
----------------
5 1 2 3 4


Sample Input-2:
---------------
4 2 5

Sample Output-2:
----------------
2 5 4
*/


import java.util.*;

class Node{
  int val;
  Node left;
  Node right;

  Node(int val){
    this.val = val;
    this.left = this.right = null;
  }
}

public class Day9P1 {
  static List<Integer> ans = new ArrayList<>();
  public static Node buildTree(int[] elements) {
        if (elements.length == 0) return null;
        Node root = new Node(elements[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        
        while (i < elements.length) {
            Node temp = queue.poll();
            
            if (i < elements.length) {
                temp.left = new Node(elements[i++]);
                queue.add(temp.left);
            }
            
            if (i < elements.length) {
                temp.right = new Node(elements[i++]);
            }
        }

        return root;
    }

    private static void printOrder(Node root) {
      if (root == null) return;
      
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      
      while (!queue.isEmpty()) {
       Node node = queue.poll();
       ans.add(node.val);
      
       if (node.left != null) queue.add(node.left);
       if (node.right != null) queue.add(node.right);
      }
  }

    public static Node flipTree(Node root){
      if(root==null) return null;

      Node temp_root=null,temp_left=null,temp_right=null;

      while(root!=null){
          temp_left=root.left;
          root.left=temp_right;
          temp_right=root.right;
          root.right=temp_root;
          temp_root=root;
          root=temp_left;
      }
        return temp_root;
    }
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] nodes = new int[input.length];

        for(int i=0;i<input.length;i++){
          nodes[i] = Integer.parseInt(input[i]);
        }
        Node tree = buildTree(nodes);
        Node fliptree = flipTree(tree);

        printOrder(fliptree);

        System.out.println(ans);
    }
}


/*
 public static void printTree(Node tree){
      if(tree==null) return;
      printTree(tree.left);
      printTree(tree.right);
      System.out.print(tree.val + " ");
    }
 */