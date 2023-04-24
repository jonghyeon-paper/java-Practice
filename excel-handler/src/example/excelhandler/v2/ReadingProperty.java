package example.excelhandler.v2;

public class ReadingProperty {

    private String columnName;
    private String variableName;
    private Class<?> variableClass;

    public ReadingProperty(String columnName, String variableName, Class<?> variableType) {
        this.columnName = columnName;
        this.variableName = variableName;
        this.variableClass = variableType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Class<?> getVariableClass() {
        return variableClass;
    }

    public void setVariableClass(Class<?> variableClass) {
        this.variableClass = variableClass;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
