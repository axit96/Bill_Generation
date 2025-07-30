package stream_apis;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"java:S106","javaS3655"})
public class stream_practice {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, 5, 7, 10, 12);
        System.out.println("Given a list "+ numbers +" use streams to filter and print only the even numbers.");
        numbers.stream().filter(c -> c%2==0).forEach(c -> System.out.printf("%s ",c));
        System.out.println();

        List<String> names = Arrays.asList("alice", "bob", "carol");
        System.out.println("Given a list "+ names +" use streams to convert names to uppercase.");
        names.stream().map(String::toUpperCase).forEach(name -> System.out.printf("%s ",name));
        System.out.println();

        List<String> fruits = Arrays.asList("Apple", "Banana", "Avocado", "apricot", "berry");
        System.out.println("Given a list "+ fruits +" count how many strings in a list start with the letter 'A'.");
        System.out.println(fruits.stream()
                .filter(word -> word.startsWith("A")).count()
                +" "+
                fruits.stream().filter(word -> word.startsWith("A")).toList());
        System.out.println();

        List<String> words = Arrays.asList("cat", "elephant", "tiger", "hippopotamus");
        System.out.println("Given a list "+ words +" use streams to find the longest string in a list.");
        System.out.println(words.stream()
                .max(Comparator.comparing(String::length))
                .get()
                + ", " +
                words.stream().max(Comparator.comparing(String::length)).get().length());
        System.out.println();

        List<Integer> nums = Arrays.asList(2, 3, 4);
        System.out.println("Given a list "+ nums +" print sum of all elements");
        System.out.println(nums.stream().map(c -> c * c).reduce(Integer::sum).get());
        System.out.println();

        List<Integer> ints = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        System.out.println("Given a list "+ ints +" print distinct elements");
        ints.stream().distinct().forEach(i-> System.out.print(i + " "));
        System.out.println();

        List<String> ws = Arrays.asList("a", "to", "tea", "ted", "bat", "bats");
        System.out.println("Given a list "+ ws +" group a list of words by their lengths using Stream API.");
        System.out.println(ws.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println();

        List<Integer> numbs = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("Given a list "+ numbs +" partition a list of numbers into even and odd, with output as a map.");
        System.out.println(numbs.stream().collect(Collectors.groupingBy(i -> i%2 == 0)));
        System.out.println();

        String s = "samplestring";
        System.out.println("Given string "+s+" find first non-repeated characters");
        System.out.println(s.chars()
                .mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(c->c,LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .get());
        System.out.println();

        List<Employee> employees = Arrays.asList(
                new Employee("John", "HR", 50000),
                new Employee("Jane", "Tech", 70000),
                new Employee("Jack", "HR", 60000),
                new Employee("Jill", "Tech", 80000)
        );
        System.out.println("Given list of employees objects " + employees.toString() +" group employees by department and, for each department, find the employee with the highest salary.");
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getPosition,Collectors.maxBy(Comparator.comparing(Employee::getSalary)))));
    }
}
