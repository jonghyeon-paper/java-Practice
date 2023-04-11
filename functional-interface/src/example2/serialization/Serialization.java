package example2.serialization;

import java.util.function.Function;

public class Serialization<T> {

    public Serialization(Function<T, String> serializationFunction) {
        this.serializationFunction = serializationFunction;
    }

    private Function<T, String> serializationFunction;

    public String serialize(T object) {
        return serializationFunction.apply(object);
    }
}
