/*
 * In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the higher clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and 
multiple officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as 
agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the maximum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the second highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, second top agent based on rank.


Sample Input-1:
---------------
5 5 4 -1 -1 2 4

Sample Output-1:
----------------
4


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
-2

 */
import java.util.*;

class Tree {
    int val;
    Tree left;
    Tree right;

    Tree(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Day8P3 {

    static int firstMax = Integer.MIN_VALUE;
    static int secondMax = -2;

    public static Tree construct(int[] nodeVals, int pos) {
        if (pos >= nodeVals.length || nodeVals[pos] == -1) return null;
        Tree root = new Tree(nodeVals[pos]);
        root.left = construct(nodeVals, 2 * pos + 1);
        root.right = construct(nodeVals, 2 * pos + 2);
        return root;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String[] nodes = sc.nextLine().split(" ");
        sc.close();
        int[] nodeVals = Arrays.stream(nodes).mapToInt(Integer::parseInt).toArray();

        Tree tree = construct(nodeVals, 0);
        inorder(tree);

        System.out.println(secondMax == Integer.MIN_VALUE ? -2 : secondMax);
    }

    public static void inorder(Tree root) {
        if (root == null) return;

        inorder(root.left);

        if (root.val > firstMax) {
            secondMax = firstMax;
            firstMax = root.val;
        } else if (root.val < firstMax && root.val > secondMax) {
            secondMax = root.val;
        }

        inorder(root.right);
    }
}
