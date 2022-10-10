package nl.belastingdienst.voetbal_vereniging.exception;

public class BadTeamNameException extends RuntimeException{

    public BadTeamNameException() {
        super();
    }

    public BadTeamNameException(String message) {
        super(message);
    }
}
