package Backend.Controller.Exceptions;

public class TestControllerNumberTooLowException extends RuntimeException {
    public TestControllerNumberTooLowException(int tooLowValue) {
        super("Value too low, currently: " + tooLowValue);
    }
}