package example.excelhandler;

public class ExcelException extends RuntimeException {

    private static final long serialVersionUID = 6782392416683121561L;

    public ExcelException() {
        super();
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
