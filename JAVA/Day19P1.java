/*

"Emphatic Pronunciation" of a given word is where we take the word and
replicate some of the letter to emphasize their impact.

Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
We define emphatic pronunciation of a word, which is derived by replicating 
a group (or single) of letters in the original word. 

So that the replicated group is atleast 3 characters or more and 
greater than or equal to size of original group. 
For example Good -> Goood is an emphatic pronunciation,
but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
In the question you are given the "Emphatic pronunciation" word, 
you have to findout how many words can legal result in the 
"emphatic pronunciation" word.

Input Format:
-------------
Line-1 -> A String contains a single word, Emphatic Pronunciation word
Line-2 -> Space seperated word/s

Output Format:
--------------
Print an integer as your result


Sample Input-1:
---------------
goood
good goodd

Sample Output-1:
----------------
1

Sample Input-2:
---------------
heeelllooo
hello hi helo

Sample Output-2:
----------------
2
*/

import java.util.*;
public class Day19P1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String emphatic = sc.nextLine();
        String[] words = sc.nextLine().split(" ");
        int count = 0;
        for(String word : words){
            if(isValidEmphatic(word, emphatic)){
                count++;
            }
        }
        System.out.println(count);
    }

    static boolean isValidEmphatic(String word, String emphatic){
        int i = 0, j = 0;
        int n = word.length(), m = emphatic.length();
        while(i < n && j < m){
            if(word.charAt(i) != emphatic.charAt(j)){
                return false;
            }
            int countWord = 0;
            char ch = word.charAt(i);
            while(i < n && word.charAt(i) == ch){
                countWord++;
                i++;
            }
            int countEmphatic = 0;
            while(j < m && emphatic.charAt(j) == ch){
                countEmphatic++;
                j++;
            }
            if(countEmphatic < countWord) return false;
            if(countEmphatic > countWord && countEmphatic < 3) return false;
        }
        return i == n && j == m;
    }
}

