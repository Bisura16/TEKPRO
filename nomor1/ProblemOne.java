package nomor1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemOne {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee("Alice", 50000));
        list.add(new Employee("bob", 70000));
        list.add(new Employee("rob", 40000));
        list.add(new Employee("john", 10000));

        // Mengubah list ke stream, lalu sorting, lalu dikembalikan ke List
        List<Employee> sortedEmp = list.stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList());

        for (Employee e : sortedEmp) {
            System.out.println(e);
        }
    }
}
