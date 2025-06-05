/*
Problem: Write a program to count each word's first index and total occurrences 
in a sentence.

Sample Input: 
apple banana apple orange banana apple

Sample Output:
apple -> first index: 0, count: 3
banana -> first index: 6, count: 2
orange -> first index: 19, count: 1

*/

import java.util.*;
public class Day57P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] words = s.split(" ");
        
        HashMap<String, Integer> map = new LinkedHashMap<>();
        List<Integer> index = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for(String w : words){
            if(!set.contains(w)){
                set.add(w);
                index.add(s.indexOf(w));
            }
            if(!map.containsKey(w)){
                map.put(w, 1);
            }
            else map.put(w, map.get(w)+1);
        }
        
        int i=0;
        for(String w : map.keySet()){
            System.out.println(w + " -> first index: " + index.get(i) + ", count: " + map.get(w));
            i++;
        }
    }
}

