package kz.hackathon.secretsantaapp.exception;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
