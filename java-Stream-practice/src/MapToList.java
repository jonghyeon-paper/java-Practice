import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapToList {

	public static void main(String[] args) {
		Map<String, String> sample = new HashMap<>();
		sample.put("key1", "DD");
		sample.put("key2", "CC");
		sample.put("key3", "EE");
		sample.put("key4", "AA");
		sample.put("key5", "BB");
		
		List<Map<String, String>> result = sample.entrySet().stream()
				.map(entrySet -> {
					Map<String, String> temp = new HashMap<>();
					temp.put(entrySet.getKey(), entrySet.getValue());
					return temp;
				})
				.collect(Collectors.toList());
		
		System.out.println(result.toString());
	}
}
