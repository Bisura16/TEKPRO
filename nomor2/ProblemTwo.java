package nomor2;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemTwo {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(101, "Joe"),
                new Student(103, "Zulkifli"),
                new Student(102, "Riza"),
                new Student(104, "Alice"),
                new Student(105, "Joshua")
        );

        List<Student> ans = students.stream() // [1]
                .sorted((s1, s2) -> { // [2]
                    
                    // Jika nama sama → bandingkan ID
                    if (s1.getName().equalsIgnoreCase(s2.getName())) {
                        return Integer.compare(s1.getId(), s2.getId()); // [3]
                    } 
                    // Jika nama berbeda → bandingkan nama (ignore case)
                    else {
                        return s1.getName().compareToIgnoreCase(s2.getName()); // [4]
                    }
                })
                .collect(Collectors.toList()); // [5]

        for (Student student : ans) {
            System.out.println(student);
        }
    }
}