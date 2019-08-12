import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import object.Computer;

public class DataGrouping {

	public static void main(String[] args) {
		List<Map<String, Object>> attributeList = new ArrayList<>();
		attributeList.add(new HashMap<String, Object>() {{put("key", "cpu");put("text", "시피유");}});
		attributeList.add(new HashMap<String, Object>() {{put("key", "ram");put("text", "램");}});
		attributeList.add(new HashMap<String, Object>() {{put("key", "vga");put("text", "그래픽");}});
		
		Map<String, Map<String, Object>> attributeMap = attributeList.stream()
			.collect(Collectors.toMap(item -> String.valueOf(item.get("key")), item -> item));
		
		attributeMap.entrySet().stream().forEach(System.out::println);
		
		List<Computer> computerList = new ArrayList<>();
		computerList.add(new Computer("Apple", "cpu", "i5"));
		computerList.add(new Computer("Apple", "ram", "8gb"));
		computerList.add(new Computer("Apple", "vga", "1080"));
		computerList.add(new Computer("LG", "cpu", "i3"));
		computerList.add(new Computer("LG", "ram", "16gb"));
		computerList.add(new Computer("LG", "vga", "내장"));
		computerList.add(new Computer("samsung", "cpu", "i7"));
		computerList.add(new Computer("samsung", "ram", "4gb"));
		computerList.add(new Computer("samsung", "vga", "5700"));
		
		Map<String, List<Computer>> computerGeoupList = computerList.stream()
			.collect(Collectors.groupingBy(Computer::getName));
		
		List<Computer> mergeComputerList = computerGeoupList.entrySet().stream()
			.map(item -> {
				Computer newItem = new Computer(item.getKey(), null, null); // or get 0 index data. 
				newItem.setAttributes(item.getValue().stream().collect(Collectors.toMap(Computer::getAttribute, Computer::getValue)));
				return newItem;
			})
			.collect(Collectors.toList());
		
		mergeComputerList.stream().forEach(System.out::println);
		
		List<Map<String, Object>> mapList = mergeComputerList.stream()
			.map(item -> {
				Map<String, Object> newItem = new HashMap<>();
				newItem.put("name", item.getName());
				newItem.putAll(item.getAttributes());
				return newItem;
			})
			.collect(Collectors.toList());
		
		mapList.stream().forEach(System.out::println);
	}

}
