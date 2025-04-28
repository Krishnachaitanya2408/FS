/*
 * A bookstore manager is analyzing the best-selling books from their records. 
Each record contains a book title, author, and publishing date in the format 
"DD-MM-YYYY".

Your task is to extract and count the number of distinct publishing years from 
the given list of best-selling books.

Input Format
------------
A string containing multiple book records, where each book entry includes a 
publishing date in "DD-MM-YYYY" format.

Output Format
--------------
Return an integer representing the total number of distinct publishing years 
in the given records.


Sample Input:
--------------
The Great Gatsby by F. Scott Fitzgerald was published on 10-04-1925. To Kill a Mockingbird by Harper Lee was published on 11-07-1960.

Sample Output:
----------------
2

Explanation:
--------------
The books were published in 1925 and 1960.
Total distinct years = 2.


Sample Input:
-------------
1984 by George Orwell was published on 08-06-1949. Animal Farm by George Orwell was published on 17-08-1945. The Catcher in the Rye by J.D. Salinger was published on 16-07-1951

Sample Output:
---------------
3

Explanation:
-------------
The books were published in 1945, 1949, and 1951.
Total distinct years = 3.


Sample Input:
---------------
Pride and Prejudice by Jane Austen was published on 28-01-1812. Sense and Sensibility by Jane Austen was published on 30-10-1812. Emma by Jane Austen was published on 23-12-1812.

Sample Output:
----------------
1

 */
import java.util.*;
import java.util.regex.*;

public class Day11P2_dateRegex {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String l = sc.nextLine();
        Set<String> set = new HashSet<>();
        for(int i=0;i<=l.length()-10;i++){
            String sub = l.substring(i, i+10);
            if(sub.matches("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-[0-9]{4}")){
                set.add(sub.substring(sub.length()-4));
            }
        }
        System.out.println(set.size());
        Set<String> res = extractYears(l);
        System.out.println(res);
        sc.close();
    }

    public static Set<String> extractYears(String input) {
        Set<String> years = new HashSet<>();
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-(\\d{4})"); // Simplified regex
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            years.add(matcher.group(1)); // Extract the year (group 1)
        }
        return years;
    }
}