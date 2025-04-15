/*
Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false
*/

import java.util.*;
public class Day24P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();
        int sum = Arrays.stream(arr).sum();
        if(sum%4!=0){
            System.out.println(false);
            return;
        }
        int tar = sum / 4;
        boolean[] used = new boolean[n];
        Arrays.sort(arr);
        System.out.println(bT(arr, tar, used, 0, 0, 4));
    }
    private static boolean bT(int[] arr, int tar, boolean[] used, int idx, int currSum, int left){
        if(left==0) return true;
        if(currSum==tar) return bT(arr, tar, used, 0, 0, left-1);
        for(int i=idx;i<arr.length;i++){
            if(!used[i] && currSum+arr[i]<=tar){
                used[i] = true;
                if(bT(arr, tar, used, i+1, currSum+arr[i], left)){
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }
}