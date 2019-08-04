import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectListToArray {

    public static void main(String[] args) {
        Map<String, String> sample = new HashMap<>();
        sample.put("key1", "DD");
        sample.put("key2", "CC");
        sample.put("key3", "EE");
        sample.put("key4", "AA");
        sample.put("key5", "BB");
        
        List<Map<String, String>> mapList = sample.entrySet().stream()
                .map(entrySet -> {
                    Map<String, String> temp = new HashMap<>();
                    temp.put("key", entrySet.getKey());
                    temp.put("value", entrySet.getValue());
                    return temp;
                })
                .collect(Collectors.toList());
        
        String[] array = mapList.stream()
                .map(map -> map.get("key"))
                .toArray(String[]::new);
        
        Stream<String> stream = Stream.of(array);
        stream.forEach(System.out::println);
    }
}
