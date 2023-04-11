package example1.concatenation;

import example1.data.Sample1;

public class ConcatenationTest {

    public static void main(String[] args) {
        Sample1 sampleData = new Sample1(1, "Dr pepper", 1.45d);

        Concatenation concatenation = new Concatenation();
        String combineResult = concatenation.combine(sampleData, data -> {
            return data.getId() + " / " + data.getName() + " / " + data.getPrice();
        });
        System.out.println(combineResult);
    }

}
