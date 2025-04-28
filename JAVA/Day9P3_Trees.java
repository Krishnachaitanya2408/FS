import java.util.*;
/*
A software development company is designing a smart home automation
system that uses sensor networks to monitor and control different devices
in a house. The sensors are organized in a hierarchical structure, where each
sensor node has a unique ID and can have up to two child nodes (left and right).

The company wants to analyze the left-most sensors in the system to determine
which ones are critical for detecting environmental changes. The hierarchy of
the sensors is provided as a level-order input, where missing sensors are
represented as -1.

Your task is to build the sensor network as a binary tree and then determine
the left-most sensor IDs at each level.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the left-most sensor IDs at each level


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 2, 4]



Sample Input-2:
---------------
3 2 4 1 5

Sample Output-2:
----------------
[3, 2, 1]

 */
public class Day9P3_Trees {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] vals = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        TreeNode root = buildTree(vals);
        List<Integer> res = balbirOppView(root);
        System.out.println(res);
        sc.close();
    }
    private static List<Integer> balbirOppView(TreeNode root){
        if(root==null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode temp = q.poll();
                if(i==0) res.add(temp.val);
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