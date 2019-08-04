import java.util.stream.Stream;

public class NestedLoopFilter {

    public static void main(String[] args) {
        String[] array1 = new String[] {"aaa", "bbb", "ccc", "ddd", "eee"};
        String[] array2 = new String[] {"aaa", "ddd", "eee"};
        
        Stream.of(array1)
            .filter(array1Item -> Stream.of(array2)
                                        .anyMatch(array2Item -> array2Item.equals(array1Item)))
            .forEach(System.out::println);
    }
}
