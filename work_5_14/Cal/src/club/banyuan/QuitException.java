package club.banyuan;

public class QuitException extends CalculatorException{
    public QuitException() {
    }

    public QuitException(String message) {
        super(message);
    }
}
