import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.lambda.Seq;

import object.Book;

public class GroupBy {

	public static void main(String[] args) {
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("파피용", "aaa", "000", 12000));
		bookList.add(new Book("고양이 울음", "aaa", "111", 10000));
		bookList.add(new Book("너의 췌장을 먹고 싶어", "aaa", "111", 14000));
		bookList.add(new Book("편의점 인간", "bbb", "555", 11000));
		bookList.add(new Book("눈에서 온 아이", "bbb", "222", 13000));
		bookList.add(new Book("빙과", "ddd", "666", 10000));
		bookList.add(new Book("잘못은 우리별에 있어", "ddd", "222", 9000));
		bookList.add(new Book("그대 눈동자에 건배", "ddd", "333", 13000));
		
		// grouped(1)
		Seq.seq(bookList.stream())
			.grouped(item -> item.getAuthor())
			.forEach(System.out::println);
		
		System.out.println();
		
		// grouped(2)
		Seq.seq(bookList.stream())
			.grouped(Book::getAuthor)
			.forEach(System.out::println);
		
		System.out.println();
		
		// grouped - sum
		Seq.seq(bookList.stream())
			.grouped(Book::getAuthor, Collectors.summingInt(Book::getPrice))
			.forEach(System.out::println);
		
		System.out.println();
		
		// grouped - min or max
		Seq.seq(bookList.stream())
			.grouped(Book::getAuthor, Collectors.minBy(Comparator.comparing(Book::getPrice)))
			.forEach(System.out::println);
		
		System.out.println();
		
		// grouped - average
		Seq.seq(bookList.stream())
			.grouped(Book::getAuthor, Collectors.averagingInt(Book::getPrice))
			.forEach(System.out::println);
		
		System.out.println();
		
		// groupBy(1)
		Seq.seq(bookList.stream())
			.groupBy(item -> item.getAuthor())
			.entrySet().stream()
				.forEach(System.out::println);
		
		System.out.println();
		
		// groupBy(2)
		Seq.seq(bookList.stream())
			.groupBy(Book::getAuthor)
			.entrySet().stream()
				.forEach(System.out::println);
		
		System.out.println();
	}

}
