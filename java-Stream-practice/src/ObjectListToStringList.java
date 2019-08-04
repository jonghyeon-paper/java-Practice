import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectListToStringList {
    
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
        
        List<String> stringList = mapList.stream()
                .map(map -> map.get("value"))
                .collect(Collectors.toList());
        
        System.out.println(stringList.toString());
    }
}
