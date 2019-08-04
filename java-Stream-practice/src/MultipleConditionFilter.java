import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MultipleConditionFilter {

    public static void main(String[] args) {
        String[] array = new String[] {"abc", "abcd", "bcd", "abcde", "cd", "cde", "cdef"};
        
        // && or ||
        System.out.println("case >> && or ||");
        Stream.of(array)
            .filter(item -> item.startsWith("a") && item.length() > 3)
            .forEach(System.out::println);
        
        
        // multiple filter
        System.out.println();
        System.out.println("case >> multiple filter");
        Stream.of(array)
            .filter(item -> item.startsWith("a"))
            .filter(item -> item.length() > 3)
            .forEach(System.out::println);
        
        // predicate
        System.out.println();
        System.out.println("case >> predicate");
        Predicate<String> predicate1 =  item -> item.startsWith("a");
        Predicate<String> predicate2 =  item -> item.length() > 3;
        Stream.of(array)
            .filter(predicate1.and(predicate2))
            .forEach(System.out::println);
        
        // negative predicate
        System.out.println();
        System.out.println("case >> negative predicate");
        Stream.of(array)
            .filter(predicate1.and(predicate2.negate()))
            .forEach(System.out::println);
        
        // inline predicate
        System.out.println();
        System.out.println("case >> inline predicate");
        Stream.of(array)
            .filter(((Predicate<String>) item -> item.startsWith("a")).and(item -> item.length() > 3)
                                                                      .and(item -> item.endsWith("e")))
            .forEach(System.out::println);
        
        // predicate list
        System.out.println();
        System.out.println("case >> predicate list");
        List<Predicate<String>> conditionList = new ArrayList<>();
        conditionList.add(predicate1);
        conditionList.add(predicate2);
        Stream.of(array)
            .filter(conditionList.stream().reduce(condition -> true, Predicate::and))
            .forEach(System.out::println);
        
        System.out.println();
        Stream.of(array)
            .filter(conditionList.stream().reduce(condition -> false, Predicate::or))
            .forEach(System.out::println);
    }

}
