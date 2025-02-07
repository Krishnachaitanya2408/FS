/*
 In a film festival, there is a lineup of movies, each with a rating. The festival 
organizer wants to find the maximum total rating of 'k' sequence of movies while 
following these rules:
    1. The sequence must be exactly k movies long.
    2. Each movie in the sequence must have a distinct rating.
    3. None of the movies in the sequence should have a restricted rating, as 
       these are reserved for special screenings.

Given an array movieRatings representing the sequence of movie ratings, an integer k 
representing the length of the sequence, and a set restrictedRatings (of size m) of 
special ratings, find the maximum total rating of any valid sequence. 
If no valid sequence exists, return -1.

Input Format:
-------------
Line-1: 3 space separated integers, n, k, m
Line-2: n space separated integers, movieRatings[].
Line-3: m space separated integers, restrictedRatings[].

Output Format:
-------------
An integer, the maximum total rating of any valid sequence


Sample Input-1:
---------------
7 3 2
1 5 4 2 9 9 9
2 9

Sample Output-1:
----------------
10

Explanation: 
------------
The sequences of movie ratings with length 3 are:
- [1, 5, 4] which meets the requirements and has a total rating of 10.
- [5, 4, 2] which does not meet the requirements because 2 is in the 
restricted set.
- [4, 2, 9] which does not meet the requirements because 2 and 9 are in 
the restricted set.
- [2, 9, 9] which does not meet the requirements because 2 and 9 are in 
the restricted set and 9 is repeated.
- [9, 9, 9] which does not meet the requirements because 9 is in 
the restricted set and repeated.

We return 10 because it is the maximum total rating of all the sequences 
that meet the conditions.


Sample Input-2:
---------------
3 3 1
4 4 4
4

Sample Output-2:
----------------
-1

Explanation: The sequences of movie ratings with length 3 are:
[4, 4, 4] which does not meet the requirements because 4 is repeated and in the 
restricted set.
We return -1 because no sequences meet the conditions.

Constraints:
------------
0 <= m <= n <=1000
k <= n
0 ≤ restrictedRatings.length ≤ 1000


*/

import java.util.*;

public class Day4P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        int[] movies = new int[n];
        int[] rest = new int[m];
        for(int i=0;i<n;i++) movies[i] = sc.nextInt();
        for(int i=0;i<m;i++) rest[i] = sc.nextInt();

        HashSet<Integer> set = new HashSet<>();
        for(int x: rest) set.add(x);

        int sum = 0, res = -1;
        Map<Integer, Integer> map = new HashMap<>();
        int l=0, r=0;
        while(r<n){
            map.put(movies[r], map.getOrDefault(movies[r], 0)+1);
            sum += movies[r];
            if(r>=k-1){
                // check freq and rest
                boolean valid = true;
                for(int x: map.keySet()){
                    if(set.contains(x) || map.get(x)>1){
                        valid = false;
                        break;
                    }
                }
                if(valid){
                    res = (sum==0) ? -1 : Math.max(res, sum);
                }
                map.put(movies[l], map.getOrDefault(movies[l], 0)-1);
                if(map.get(movies[l])<=0) map.remove(movies[l]);
                sum -= movies[l];
                l++;
            }
            r++;
        }
        System.out.println(res);

        sc.close();
    }
}