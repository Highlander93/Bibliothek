package Backend;

public class CustomizedExceptions {
    public static class IncorrectSelection extends Exception {
        public IncorrectSelection(String errorMessage) {
            super(errorMessage);
            System.out.println(errorMessage);
        }
    }
}
