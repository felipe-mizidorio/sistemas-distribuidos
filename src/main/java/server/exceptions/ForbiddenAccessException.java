package server.exceptions;

public class ForbiddenAccessException extends ServerResponseException {

    public ForbiddenAccessException() {
        super(403, "Usuario não tem permissão para acessar este recurso.");
    }

    public ForbiddenAccessException(final String userId) {
        super(403, "Usuario '%s' não tem permissão para acessar este recurso.".formatted(userId));
    }
}
