import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.jooq.lambda.tuple.Tuple;

import object.Author;
import object.Book;

public class DoubleJoinNoCondition {

	public static void main(String[] args) {
		List<Author> authorList = new ArrayList<>();
		authorList.add(new Author("aaa", "000", "korea", 28));
		authorList.add(new Author("bbb", "111", "korea", 24));
		authorList.add(new Author("ccc", "000", "korea", 32));
		authorList.add(new Author("ddd", "222", "korea", 27));
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("파피용", "aaa", "000", 12000));
		bookList.add(new Book("편의점 인간", "bbb", "555", 11000));
		bookList.add(new Book("빙과", "ddd", "666", 10000));
		
		// java - print
		Supplier<Stream<Book>> bookStream = () -> bookList.stream();
		authorList.stream()
			.flatMap(v1 -> bookStream.get()
					                 .map(v2 -> Tuple.tuple(v1, v2)))
			.forEach(System.out::println);
	}
}
