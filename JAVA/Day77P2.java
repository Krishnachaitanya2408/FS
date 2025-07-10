/*
Given a list of EventAttendance (eventId, attendeeName, durationMinutes), 
Your task is to consider only attendees who stayed ≥ 60 minutes.
For each event, display the Event ID (ascending order), List of qualified 
attendee names (alphabetically sorted) and Count of such attendees.

Example 1
---------
Sample Input:
4
E101 John 90
E101 Alice 55
E101 Zara 75
E102 Mark 120

Sample output:
E101 [John, Zara] Count=2
E102 [Mark] Count=1

Example 2
---------
Sample Input:
11
E502 Carl 90
E502 Dan 45
E501 Ana 100
E502 Evan 75
E501 Beth 61
E502 Fred 20
E301 Ron 30
E301 Tony 60
E302 Lily 75
E302 Kevin 50
E301 Maya 90

Sample Output:
E301 [Maya, Tony] Count=2
E302 [Lily] Count=1
E501 [Ana, Beth] Count=2
E502 [Carl, Evan] Count=2

*/

import java.util.*;
import java.util.stream.*;

class EventAttendance{
    String id;
    String name;
    int mins;
    public EventAttendance(String id, String name, int mins){
        this.id = id;
        this.name = name;
        this.mins = mins;
    }
}
public class Day77P2{
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        sc.nextLine();
        List<EventAttendance> list = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            String[] s = sc.nextLine().split(" ");
            list.add(new EventAttendance(s[0], s[1], Integer.parseInt(s[2])));
        }
        
        Map<String,List<String>> res = list.stream().filter(e-> e.mins >=60)
                                            .collect(Collectors.groupingBy(
                                                e -> e.id, Collectors.mapping(e-> e.name, Collectors.toList())));
                                
        res.entrySet().stream().sorted(Map.Entry.comparingByKey())
                        .forEach(entry -> {
                            String id = entry.getKey();
                            List<String> names = entry.getValue();
                            Collections.sort(names);
                            System.out.println(id + " " + names + " Count=" + names.size());
                        });
        
    }
}