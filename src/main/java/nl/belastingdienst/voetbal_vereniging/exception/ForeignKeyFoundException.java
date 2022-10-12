package nl.belastingdienst.voetbal_vereniging.exception;

public class ForeignKeyFoundException extends RuntimeException{

    public ForeignKeyFoundException() {
        super();
    }

    public ForeignKeyFoundException(String message) {
        super(message);
    }
}
