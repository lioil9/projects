package club.banyuan;

public class IllegalInputException extends CalculatorException{

    public IllegalInputException() {
    }

    public IllegalInputException(String message) {
        super(message);
    }

}
