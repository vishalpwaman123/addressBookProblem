package addressbook;

public class addressBookException extends Throwable {

    public enum ExceptionType {
        CANNOT_CREATE_FILE,ENTERED_EMPTY;
    }
    ExceptionType type;

    public addressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
