import java.util.*;
public class Copart_Solutions {
  static Scanner sc;
  public static void main(String... args){
    sc = new Scanner(System.in);
    p3();
  } 

  public static void p3(){
    String s1 = sc.nextLine();
    String s2 = sc.nextLine();

    for(int i=0;i<s2.length();i++){
      int j=0;
      while(j<s1.length() && s2.charAt(i+j)==s1.charAt(j)){
        j++;
      }

      if(j==s1.length()) {
        System.out.println(true);
        return;
      }
    }
    System.out.println(false);
  }


  public static void p1(){
    String s = sc.nextLine();

    Map<Character, Integer> map = new LinkedHashMap<>();
    for(char c : s.toCharArray()){
      map.put(c, map.getOrDefault(c, 0)+1);
    }

    for(Map.Entry<Character, Integer> entry : map.entrySet()){
      if(entry.getValue()> 1){
        System.out.println(entry.getKey() + " " + entry.getValue());
      }
    }
  }
}
