package example1.concatenation;

import java.util.function.Function;

public class Concatenation {

    public <T> String combine(T object, Function<T, String> conbineFunction) {
        return new StringBuffer().append("Combine : ")
                .append(conbineFunction.apply(object))
                .toString();
    }
}
