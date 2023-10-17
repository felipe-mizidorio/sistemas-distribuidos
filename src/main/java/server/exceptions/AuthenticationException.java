package server.exceptions;

public class AuthenticationException extends ServerResponseException {
    public AuthenticationException() {
        super(401, "Não foi possivel autenticar o usuario.");
    }
}
