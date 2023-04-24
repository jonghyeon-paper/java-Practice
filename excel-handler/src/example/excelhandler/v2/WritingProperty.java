package example.excelhandler.v2;

import java.util.function.Function;

public class WritingProperty<T> {

    private String columnName;
    private Function<T, Object> combiner;

    public WritingProperty(String columnName, Function<T, Object> combiner) {
        this.columnName = columnName;
        this.combiner = combiner;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Function<T, Object> getCombiner() {
        return combiner;
    }

    public void setCombiner(Function<T, Object> combiner) {
        this.combiner = combiner;
    }
}
