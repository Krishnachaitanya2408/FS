/*
Mr Gnanesh is working with inteegrs,
He is given a list of N tokens, the tokens are printed a number on it.
You are given the list of numbers printed on Tokens as a list[] of size N.
You need to help Mr Gnanesh, to find the top F frequent numbers printed 
on tokens.

Note: If the frequency of two tokens is same, pick the numbers in sorted order(Asc).

Input Format:
----------------
Line-1 -> Two Integers, N and F
Line-2 -> N space separated integers, list[]

Output Format:
------------------
Print a list of integers of size F, top F frequent elements.


Sample Input-1:
---------------
8 3
2 1 2 1 3 2 1 4

Sample Output-1:
----------------
[1, 2, 3]

Explanation: 
------------
2 occurs 3 times, 1 occurs 3 times, 3 occurs 1 time, 4 occurs 1 time.
So the top 3 tokens are 1,2,3.


Sample Input-2:
---------------
10 3
1 4 2 4 2 2 3 4 1 3

Sample Output-2:
----------------
[2, 4, 1]
*/


import java.util.*;

public class Day78P3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int F = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());

        list.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue(); // freq desc
            } else {
                return a.getKey() - b.getKey(); // num asc
            }
        });

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < F && i < list.size(); i++) {
            result.add(list.get(i).getKey());
        }

        System.out.println(result);
    }
}
