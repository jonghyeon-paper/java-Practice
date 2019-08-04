import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import object.Author;

public class Sort {
	
	@SuppressWarnings("serial")
	private static final List<Author> authorList = new ArrayList<Author>() {
		{
			add(new Author("aaa", "000", "korea", 28));
			add(new Author("bbb", "111", "korea", 24));
			add(new Author("ccc", "000", "korea", 27));
			add(new Author("ddd", "222", "korea", 32));
		}
	};
	
	@SuppressWarnings("serial")
	private static final List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>() {
		{
			add(new HashMap<String, Object>() {
				{
					put("title", "파피용");
					put("author", "aaa");
					put("publisher", "000");
					put("price", 12000);
				}
			});
			add(new HashMap<String, Object>() {
				{
					put("title", "편의점 인간");
					put("author", "bbb");
					put("publisher", "555");
					put("price", 11000);
				}
			});
			add(new HashMap<String, Object>() {
				{
					put("title", "빙과1");
					put("author", "ddd");
					put("publisher", "333");
					put("price", 10500);
				}
			});
			add(new HashMap<String, Object>() {
				{
					put("title", "빙과2");
					put("author", "ddd");
					put("publisher", "333");
					put("price", 10000);
				}
			});
		}
	};
	
	public static void main(String[] args) {
		// map sorted
		System.out.println("// map sorted");
		Map<String, String> sample = new HashMap<>();
		sample.put("key1", "DD");
		sample.put("key2", "CC");
		sample.put("key3", "EE");
		sample.put("key4", "AA");
		sample.put("key5", "BB");
		
		sample.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(System.out::println);
		
		// single object sorted
		System.out.println();
		System.out.println("// single object sorted");
		Arrays.asList("DD", "CC", "EE", "AA", "BB").stream()
			.sorted()
			.forEach(System.out::println);
		
		// single object reverse sorted
		System.out.println();
		System.out.println("// single object reverse sorted");
		Arrays.asList("DD", "CC", "EE", "AA", "BB").stream()
			.sorted(Comparator.reverseOrder())
			.forEach(System.out::println);
		
		// single object naturalOrder sorted
		System.out.println();
		System.out.println("// single object naturalOrder sorted");
		Arrays.asList("DD", "CC", "EE", "AA", "BB").stream()
			.sorted(Comparator.naturalOrder())
			.forEach(System.out::println);
		
		// object sorted - implements Comparable
		System.out.println();
		System.out.println("// object sorted - implements Comparable");
		authorList.stream()
			.sorted()
			.forEach(System.out::println);
		
		// object sorted - use Comparator.comparing(1)
		System.out.println();
		System.out.println("// object sorted - use Comparator.comparing(1)");
		authorList.stream()
			.sorted(Comparator.comparing(Author::getCompany))
			.forEach(System.out::println);
		
		// object sorted - use Comparator.comparing(2)
		System.out.println();
		System.out.println("// object sorted - use Comparator.comparing(2)");
		authorList.stream()
			.sorted(Comparator.comparing(item -> item.getCompany()))
			.forEach(System.out::println);
		
		// object sorted - use Comparator.comparing(3)
		System.out.println();
		System.out.println("// object sorted - use Comparator.comparing(3)");
		authorList.stream()
			.sorted(Comparator.<Author, String>comparing(item -> item.getCompany()))
			.forEach(System.out::println);
		
		// object sorted - use Comparator.comparing(4)
		System.out.println();
		System.out.println("// object sorted - use Comparator.comparing(4)");
		authorList.stream()
			.sorted(Comparator.comparing(Author::getCompany, String.CASE_INSENSITIVE_ORDER))
			.forEach(System.out::println);
		
		// object sorted - use Comparator.naturalOrder
		System.out.println();
		System.out.println("// object sorted - use Comparator.naturalOrder");
		authorList.stream()
			.sorted(Comparator.naturalOrder())
			.forEach(System.out::println);
		
		// object sorted - custom Comparator(1)
		System.out.println();
		System.out.println("// object sorted - custom Comparator(1)");
		authorList.stream()
			.sorted((a, b) -> a.getCompany().compareTo(b.getCompany()))
			.forEach(System.out::println);
		
		// object sorted - custom Comparator(2)
		System.out.println();
		System.out.println("// object sorted - custom Comparator(2)");
		authorList.stream()
			.sorted((a, b) -> Integer.compare(a.getAge(), b.getAge()))
			.forEach(System.out::println);
		
		// object sorted - complex condition
		System.out.println();
		System.out.println("//object sorted - complex condition");
		authorList.stream()
			.sorted(Comparator.comparing(Author::getCompany)
						.thenComparing(Author::getAge))
			.forEach(System.out::println);
		
		// map list sorted - no stream. original data sorted.
		System.out.println();
		System.out.println("// map list sorted - no stream. original data sorted.");
		bookList.sort(Comparator.comparing(m -> (String) m.get("title")));
		System.out.println(bookList);
		
		// map list sorted(1)
		System.out.println();
		System.out.println("// map list sorted(1)");
		bookList.stream()
			.sorted(Comparator.comparing(m -> (String) m.get("publisher")))
			.forEach(System.out::println);
		
		// map list sorted(2)
		System.out.println();
		System.out.println("// map list sorted(2)");
		bookList.stream()
			.sorted(Comparator.<Map<String, Object>, String>comparing(m -> (String) m.get("publisher")))
			.forEach(System.out::println);
		
		// map list sorted(3)
		System.out.println();
		System.out.println("// map list sorted(3)");
		bookList.stream()
			.sorted((m1, m2) -> String.valueOf(m1.get("publisher")).compareTo(String.valueOf(m2.get("publisher"))))
			.forEach(System.out::println);
		
		// map list sorted - complex condition
		System.out.println();
		System.out.println("//object sorted - complex condition");
		bookList.stream()
			.sorted(Comparator.<Map<String, Object>, String>comparing(m -> (String) m.get("publisher"))
						.thenComparing(m -> (Integer) m.get("price")))
			.forEach(System.out::println);
	}
}
