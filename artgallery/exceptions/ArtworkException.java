package artgallery.exceptions;   

//CONCEPT: Custom Exception Class (Exception Handling)
//We extend the built-in "Exception" class to create our own
//custom checked exception specific to our Art Gallery system.

public class ArtworkException extends Exception {

    //instance variable to store an error code
    private int errorCode;//private means only methods inside this class can access it directly

    //constructor 1: Only message
    public ArtworkException(String message) {
        super(message);   // calls Exception's constructor with the message
        this.errorCode = 0;
    }

    //constructor 2: Message + error code (Constructor Overloading)
    public ArtworkException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    //getter method for error code
    public int getErrorCode() {
        return errorCode;
    }
}

