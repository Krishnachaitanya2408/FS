/*Design a Java application to simulate a university’s student course registration 
system. 

This system should:
 - Register Undergraduate and Postgraduate students
 - Enforce course registration limits based on student type
 - Throw exceptions when course limits are exceeded
 - Sort and display student records
 - Demonstrate key OOP principles such as inheritance, abstract classes, interfaces, 
 - custom exceptions, encapsulation, and comparators

Implement the following:
--------------------------
✅ Interface: Printable
	Method: void printDetails()

✅ Class: Course
	Fields: code, title, credits
	Constructor and toString() → returns "code - title"

✅ Abstract Class: Student implements Printable
	Fields: name, id, List<Course>
	Abstract Method: int getMaxCourses()
	Method: registerCourse(Course) → throws CourseLimitExceededException 
	              if the limit is exceeded
	toString() → returns "name (id)"
	printDetails() → prints student info and registered courses

✅ Class: Undergraduate extends Student
	Method: 
		- getMaxCourses() returns 3

✅ Class: Postgraduate extends Student
	Method: 
		- getMaxCourses() returns 2

✅ Class: CourseLimitExceededException extends Exception
	Custom exception with a message

✅ Class: StudentNameComparator implements Comparator<Student>
	Sorts students alphabetically by name (case-insensitive)

✅ Class: University implements Printable
	Fields: name, List<Student>
	Methods:
		- addStudent(Student)
		- printAllStudents()
		- printSortedStudentsByName() → uses StudentNameComparator
 
Sample Input:
-------------
Tech University
1
UG Alice 101 4
CS101 DataStructures 4
CS102 AI 3
CS103 OS 4
CS104 DBMS 3


Sample Output:
-------------
Alice cannot register for more than 3 courses.
All students:
Alice (101)
  Registered: CS101 - DataStructures
  Registered: CS102 - AI
  Registered: CS103 - OS
Sorted students by name:
Alice (101)
  Registered: CS101 - DataStructures
  Registered: CS102 - AI
  Registered: CS103 - OS
  

*/

import java.util.*;

// 🚫 DO NOT MODIFY THIS MAIN CLASS
public class Day76P2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String universityName = sc.nextLine();
        int studentCount = Integer.parseInt(sc.nextLine());

        University university = new University(universityName);

        for (int i = 0; i < studentCount; i++) {
            String[] studentInfo = sc.nextLine().split(" ");
            String type = studentInfo[0];
            String name = studentInfo[1];
            int id = Integer.parseInt(studentInfo[2]);
            int courseCount = Integer.parseInt(studentInfo[3]);

            Student student = null;
            if (type.equalsIgnoreCase("UG")) {
                student = new Undergraduate(name, id);
            } else if (type.equalsIgnoreCase("PG")) {
                student = new Postgraduate(name, id);
            }

            for (int j = 0; j < courseCount; j++) {
                String[] courseInfo = sc.nextLine().split(" ");
                Course c = new Course(courseInfo[0], courseInfo[1], Integer.parseInt(courseInfo[2]));
                try {
                    student.registerCourse(c);
                } catch (CourseLimitExceededException e) {
                    System.out.println(e.getMessage());
                }
            }

            university.addStudent(student);
        }

        System.out.println("All students:");
        university.printAllStudents();

        System.out.println("\nSorted students by name:");
        university.printSortedStudentsByName();
    }
}


// ✅ Printable interface
interface Printable {
    void printDetails();
}

// ✅ Course class
class Course {
    private String code;
    private String title;
    private int credits;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return code + " - " + title;
    }
}

// ✅ Abstract Student class
abstract class Student implements Printable {
    private String name;
    private int id;
    private List<Course> courses;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public abstract int getMaxCourses();

    public void registerCourse(Course course) throws CourseLimitExceededException {
        if (courses.size() >= getMaxCourses()) {
            throw new CourseLimitExceededException(
                name + " cannot register for more than " + getMaxCourses() + " courses."
            );
        }
        courses.add(course);
    }

    public String toString() {
        return name + " (" + id + ")";
    }

    @Override
    public void printDetails() {
        System.out.println(this);
        for (Course c : courses) {
            System.out.println("  Registered: " + c);
        }
    }
}

// ✅ Undergraduate student
class Undergraduate extends Student {
    public Undergraduate(String name, int id) {
        super(name, id);
    }

    @Override
    public int getMaxCourses() {
        return 3;
    }
}

// ✅ Postgraduate student
class Postgraduate extends Student {
    public Postgraduate(String name, int id) {
        super(name, id);
    }

    @Override
    public int getMaxCourses() {
        return 2;
    }
}

// ✅ Custom exception
class CourseLimitExceededException extends Exception {
    public CourseLimitExceededException(String message) {
        super(message);
    }
}

// ✅ Comparator for sorting students by name
class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.toString().compareToIgnoreCase(s2.toString());
    }
}

// ✅ University class
class University implements Printable {
    private String name;
    private List<Student> students;

    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void printAllStudents() {
        for (Student s : students) {
            s.printDetails();
        }
    }

    public void printSortedStudentsByName() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(new StudentNameComparator());
        for (Student s : sorted) {
            s.printDetails();
        }
    }

    @Override
    public void printDetails() {
        printAllStudents();
    }
}
