import java.util.*;
import java.util.stream.IntStream;
public class Day3P1{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxFreq = sc.nextInt();
        int maxArea = sc.nextInt();
        
        int sizes[] = IntStream.range(0,n).map(i -> sc.nextInt()).toArray();
        sc.close();
        
        Map<Integer, Integer> freq = new HashMap<>();
        int a = 0, res = -1, j=0;

        for(int i=0;i<n;i++){
            a += sizes[i];
            freq.put(sizes[i], freq.getOrDefault(sizes[i],0)+1);
            
            while(a > maxArea || (freq.containsKey(sizes[i]) && freq.get(sizes[i])>maxFreq)){
                a -= sizes[j];
                freq.put(sizes[j], freq.get(sizes[j]) - 1);
                if(freq.get(sizes[j])==0) freq.remove(sizes[j]);
                j++;
            }
            res = Math.max(res, i-j+1);
        }
        System.out.println(res);
    }
}