package object;

import java.util.ArrayList;
import java.util.List;

public class Author implements Comparable<Author> {

	private String name;
	private String company;
	private String conuntry;
	private Integer age;
	private List<Book> bookList;
	
	public Author(String name, String company, String conuntry, Integer age) {
		this.name = name;
		this.company = company;
		this.conuntry = conuntry;
		this.age = age;
		this.bookList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getConuntry() {
		return conuntry;
	}

	public void setConuntry(String conuntry) {
		this.conuntry = conuntry;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", company=" + company + ", conuntry=" + conuntry + ", age=" + age
				+ ", bookList=" + bookList + "]";
	}
	
	@Override
	public int compareTo(Author author) {
		return this.getCompany().compareTo(author.getCompany());
	}
}
