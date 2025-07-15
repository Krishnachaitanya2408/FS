/*
There are M*N buckets of milk, all the buckets are equal in size (in ltrs).
The buckets are initially filled with different amounts of milk in liters.

You are given the amount of milk in the buckets as a grid of size M x N, 
buckets[][]. You need to make that all the buckets have same quantity of Milk.
You are allowed to perform one operation either adding M liters of Milk
or take away M liters of Milk from the bucket in one step.

Your task is to return the minimum number of steps required to make 
all the buckets in the bucket grid contains same amount of Milk. 
If it is not possible, return -1.

Input Format:
-----------------
Line-1: three space sepaarted integers, A, B and M.
Next A lines: B space sepaarted integers, amount of milk in liters.

Ourput Format:
-------------------
Print the integer result.


Sample Input-1:
-----------------
2 3 5
5 10 15
20 25 40

Sample Output-1:
-------------------
11

Explanation: 
------------
We can make every bucket has equal amount of Milk to 4liters by doing
the following: 
- Add 5ltrs milk to 5ltrs bucket 3 times.
- Add 5ltrs milk to 10ltrs bucket 2 times.
- Add 5ltrs milk to 15ltrs bucket 1 time.
- Takeaway 5ltrs milk from 25ltrs bucket 1 time.
- Takeaway 5ltrs milk from 40ltrs bucket 4 times.
A total of 11 operations required.


Sample Input-2:
-----------------
3 3 3
1 2 3 4
5 6 7 8
9 19 11 12

Sample Output-2:
-------------------
-1
*/


import java.util.*;
public class Day80P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int l = sc.nextInt();
        
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                list.add(sc.nextInt());
            }
        }
        
        Collections.sort(list);
        
        int steps = 0;
        int t = list.get(m*n/2);
        
        for(int i=0;i<m*n;i++){
            if(i==m*n/2) continue;
            
            int cur = Math.abs(t-list.get(i));
            if(cur%l!=0){
                System.out.println(-1);
                return;
            }
            steps+= cur/l;
        }
    
        System.out.println(steps);   
    }
}