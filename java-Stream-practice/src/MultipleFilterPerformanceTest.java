import java.util.Arrays;
import java.util.stream.Collectors;

public class MultipleFilterPerformanceTest {

	public static void main(String[] args) {
		String[] array = new String[] {"abc", "abcd", "bcd", "abcde", "cd", "cde", "cdef"};
		
		Arrays.stream(array)
			.filter(item -> {
				System.out.println("filter1");
				return true;
			})
			.filter(item -> {
				System.out.println("filter2");
				return true;
			})
			.collect(Collectors.toList());
	}
}
