package nl.belastingdienst.voetbal_vereniging.exception;

public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L; // waarom hebben we een serial version UID nodig?

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

}
