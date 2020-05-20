package club.banyuan;

public class UnknownPersonException extends Exception{
    public UnknownPersonException() {
    }

    public UnknownPersonException(String message) {
        super(message);
    }
}
