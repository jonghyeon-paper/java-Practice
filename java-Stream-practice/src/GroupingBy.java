import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import object.Book;

public class GroupingBy {

	public static void main(String[] args) {
		// refenence - https://www.baeldung.com/java-groupingby-collector
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("파피용", "aaa", "000", 12000));
		bookList.add(new Book("고양이 울음", "aaa", "111", 10000));
		bookList.add(new Book("너의 췌장을 먹고 싶어", "aaa", "111", 14000));
		bookList.add(new Book("편의점 인간", "bbb", "555", 11000));
		bookList.add(new Book("눈에서 온 아이", "bbb", "222", 13000));
		bookList.add(new Book("빙과", "ddd", "666", 10000));
		bookList.add(new Book("잘못은 우리별에 있어", "ddd", "666", 9000));
		bookList.add(new Book("그대 눈동자에 건배", "ddd", "666", 13000));
		
		
		// use Collectors.groupingBy
		System.out.println("// use Collectors.groupingBy");
		Map<String, List<Book>> result1 = bookList.stream()
			.collect(Collectors.groupingBy(Book::getAuthor));
		
		System.out.println(result1);
		
		// use Collectors.groupingBy - sum
		System.out.println();
		System.out.println("// use Collectors.groupingBy - sum");
		Map<String, Integer> result2 = bookList.stream()
				.collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingInt(Book::getPrice)));
			
		System.out.println(result2);
		
		// use Collectors.groupingBy - min or max
		System.out.println();
		System.out.println("// use Collectors.groupingBy - min or max");
		List<Book> result3 = bookList.stream()
				.collect(Collectors.groupingBy(Book::getAuthor, Collectors.minBy(Comparator.comparingInt(Book::getPrice))))
				.entrySet().stream()
					.map(entry -> entry.getValue().get())
					.collect(Collectors.toList());
		
		System.out.println(result3);
		
		// use Collectors.groupingBy - average
		System.out.println();
		System.out.println("// use Collectors.groupingBy - average");
		Map<String, Double> result4 = bookList.stream()
				.collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingInt(Book::getPrice)));
		
		System.out.println(result4);
		
		// use Collectors.groupingBy - to output
		System.out.println();
		System.out.println("// use Collectors.groupingBy - to output(1)");
		List<Map<String, Object>> result5 = bookList.stream()
				.collect(Collectors.groupingBy(Book::getAuthor))
				.entrySet().stream()
					.map(entry -> {
						Map<String, Object> item = new LinkedHashMap<>();
						item.put("author", entry.getKey());
						
						Optional<Book> maximumBook = entry.getValue().stream().max(Comparator.comparing(Book::getPrice));
						item.put("maxmumProce", maximumBook.get().getPrice());
						
						Optional<Book> minimumBook = entry.getValue().stream().min(Comparator.comparing(Book::getPrice));
						item.put("minimumProce", minimumBook.get().getPrice());
						
						Double averagePrice = entry.getValue().stream().mapToInt(Book::getPrice).average().orElse(0d);
						item.put("averagePrice", averagePrice);
						
						return item;
					}).collect(Collectors.toList());
			
		System.out.println(result5);
	}
}
