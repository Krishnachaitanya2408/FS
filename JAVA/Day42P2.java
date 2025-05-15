/*
Mr NOkayya is AI developer, He is given a dictionary, he want to implement a 
an Word-Corrector application that checks user-word and corrects it.

For a given user-word, the Word-Corrector handles two types of validations:

1) If the user-word matches a word in the dictionary (case-insensitive), 
then the user-word is returned with the matched word in the dictionary.
    Ex-1: dict = ["kmit"], user-word = "KmIt": word-corrector = "kmit"
	Ex-2: dict = ["Kmit"], user-word = "kmit": word-corrector = "Kmit"
	Ex-3: dict = ["kmit"], user-word = "kmit": word-corrector = "kmit"
	
2) If after replacing the vowels of the user-word with any vowel individually,
it matches a word in the dictionary (case-insensitive), 
then the user-word is returned with the matched word in the dictionary.
	Ex-1: dict = ["KmIt"], user-word = "kmet": word-corrector = "KmIt"
	Ex-2: dict = ["KmIt"], user-word = "kmmit": word-corrector = "" (no match)
	Ex-3: dict = ["KmIt"], user-word = "kit": word-corrector = "" (no match)

In addition to the above conditions, the word-corrector app works
with the following precedence rules:
 - When the user-word exactly matches a word in the dictionary (case-sensitive), 
    you should return the same word back.
 - When the user-word matches a word up to validation-1, 
    you should return the first such match in the dictionary.
 - When the user-word matches a word up to validation-2, 
    you should return the first such match in the dictionary.
 - If the user-word has no matches in the dictionary, 
    you should return the empty string.

You are given some user-words[], return a list of words result[], 
where result[i] is the corrected words by the Word-Corrector app for 
user-word = user-words[i].


Input Format:
-------------
Line-1: comma separated strings, dictionary[].
Line-2: comma separated strings, user-words[].

Output Format:
--------------
List of corrected user-words[] by Word-Corrector app.


Sample Input-1:
---------------
LiTe,lite,bare,Bare
lite,Lite,LiTe,Bare,BARE,Bear,bear,leti,leet,leto

Sample Output-1:
----------------
[lite, LiTe, LiTe, Bare, bare, , , LiTe, , LiTe]


Sample Input-2:
---------------
kmit,ngit,kmec
KmOT,NHIT,KMIC

Sample Output-2:
----------------
[kmit, , kmec]

 
*/

import java.util.*;
public class Day42P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        List<String> dict = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
        List<String> words = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
        
        List<String> res = new ArrayList<>();
        
        for(String w : words){
            if(dict.contains(w)){
                res.add(w);
            }
            else{
                boolean f = false;
                for(String d : dict){
                    if(d.equalsIgnoreCase(w)){
                        res.add(d);
                        f = true;
                        break;
                    }
                }
                
                if(!f){
                    String cur = "";
                    for(String d : dict){
                        if(check(w.toLowerCase(), d.toLowerCase())){
                            cur = d;
                            break;
                        }
                    }
                    res.add(cur.isEmpty()?"":cur);
                }
            }
        }
        
        System.out.println(res);
    }
    
    public static boolean check(String w, String d){
        if(w.length()!=d.length()) return false;
        
        String vow = "aeiou";
        
        for(int i=0;i<w.length();i++){
            char wc = w.charAt(i);
            char dc = d.charAt(i);
            if(wc!=dc &&
                !(vow.indexOf(wc)!=-1
                    && vow.indexOf(dc)!=-1)) return false;
        }
        return true;
    }
}