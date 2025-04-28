/*
A security team is setting up surveillance cameras in a multi-floor building.
Each floor has a certain number of cameras, and every camera is assigned
a resolution value (in megapixels). The placement follows a hierarchical
structure, similar to a tree:
	- Floor 0 (Ground Floor) has a single main camera (root camera).
	- From the next floor onward, each camera can have at most two sub-cameras,
	one on the left side and one on the right side.
	- If a camera does not have a sub-camera at a position, it is represented as -1.

The goal is to identify the camera with the highest resolution on each floor to
ensure optimal security coverage.

Input Format:
-------------
A single line of space separated integers, the resolution values of cameras

Output Format:
--------------
A list of integers, where eech integer represents the maximum resolution camera
on that floor.


Sample Input-1:
---------------
2 4 3 6 4 -1 9

Sample Output-1:
----------------
[2, 4, 9]


Sample Input-2:
---------------
3 4 7 7 3 8 4

Sample Output-2:
----------------
[3, 7, 8]

 */
import java.util.*;
public class Day9P1_Trees {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int[] vals = Arrays.stream(sc.nextLine().split(" "))
              .mapToInt(Integer::parseInt)
              .toArray();
      TreeNode root = buildTree(vals, 0);
      List<Integer> res = getMaxCam(root);
      System.out.println(res);
      sc.close();
  }
  private static List<Integer> getMaxCam(TreeNode root){
      List<Integer> res = new ArrayList<>();
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()){
          int size = q.size();
          int tempMax = Integer.MIN_VALUE;
          for(int i=0;i<size;i++){
              TreeNode temp = q.poll();
              if(temp!=null){
                  tempMax = Math.max(tempMax, temp.val);
                  q.offer(temp.left);
                  q.offer(temp.right);
              }
          }
          if(tempMax!=Integer.MIN_VALUE) res.add(tempMax);
      }
      return res;
  }
  private static TreeNode buildTree(int[] vals, int level){
      if(level>=vals.length) return null;
      if(vals[level]==-1) return null;
      TreeNode root = new TreeNode(vals[level]);
      root.left = buildTree(vals, 2*level+1);
      root.right = buildTree(vals, 2*level+2);
      return root;
  }
}