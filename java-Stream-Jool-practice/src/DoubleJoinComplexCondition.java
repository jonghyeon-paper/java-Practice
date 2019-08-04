import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;

import object.Author;
import object.Book;

public class DoubleJoinComplexCondition {

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
        
        // java - use tuple - &&
        authorList.stream()
            .flatMap(v1 -> bookList.stream()
                                   .filter(v2 -> Objects.equals(v1.getName(), v2.getAuthor()) && Objects.equals(v1.getCompany(), v2.getPublisher()))
                                   .map(v2 -> Tuple.tuple(v1, v2)))
            .forEach(System.out::println);
        
        // java - use tuple - multiple filter
        authorList.stream()
            .flatMap(v1 -> bookList.stream()
                                   .filter(v2 -> Objects.equals(v1.getName(), v2.getAuthor()))
                                   .filter(v2 -> Objects.equals(v1.getCompany(), v2.getPublisher()))
                                   .map(v2 -> Tuple.tuple(v1, v2)))
            .forEach(System.out::println);
        
        //java - use tuple - Predicate
        authorList.stream()
            .flatMap(v1 -> bookList.stream()
                                   .filter(((Predicate<Book>) v2 -> Objects.equals(v1.getName(), v2.getAuthor())).and(v2 -> Objects.equals(v1.getCompany(), v2.getPublisher())))
                                   .map(v2 -> Tuple.tuple(v1, v2)))
            .forEach(System.out::println);
        
        // jool - &&
        Seq.seq(authorList.stream())
            .innerJoin(Seq.seq(bookList.stream()), (t, u) -> Objects.equals(t.getName(), u.getAuthor()) && Objects.equals(t.getCompany(), u.getPublisher()))
            .forEach(System.out::println);
        
        // jool - BiPredicate
        Seq.seq(authorList.stream())
            .innerJoin(Seq.seq(bookList.stream()), ((BiPredicate<Author, Book>) (t, u) -> Objects.equals(t.getName(), u.getAuthor())).and((t, u) -> Objects.equals(t.getCompany(), u.getPublisher())))
            .forEach(System.out::println);
    }
}
