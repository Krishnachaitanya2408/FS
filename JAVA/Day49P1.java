/*
Mr Govind is working with words in english.
He found something interesting between the words.
The properties of those words are as follows:
	- The words have same set of letters and occurences of each letter is also same.
	- For example, read and dear are the two words having same letters,
	and each letter appeared for same number of times in each word.
	- Only the positions of the letters may vary in the words.

You will be given the list of words, Your task is to find and
group the words which have the properties mentioned above.
And print them as list of list of words as shown in the samples. 

Input Format:
-------------
Line-1: A single line space space-separated words, list[].

Output Format:
--------------
Print the list of list of strings.


Sample Input-1:
---------------
tear tera dare dear read rate tare earn near rena

Sample Output-1:
----------------
[[tear, tera, rate, tare], [dare, dear, read], [earn, near, rena]]


Sample Input-2:
---------------
rate tar eat tare tear tea rat

Sample Output-2:
----------------
[[rate, tare, tear], [tar, rat], [eat, tea]] 
*/

import java.util.*;
public class Day49P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        
        Map<String, List<String>> map = new LinkedHashMap<>();
        
        for(String w : words){
            char[] ch = w.toCharArray();
            Arrays.sort(ch);
            String cur = new String(ch);
            map.computeIfAbsent(cur, k -> new ArrayList<>()).add(w);
        }
        
        System.out.println(map.values());
    }
}