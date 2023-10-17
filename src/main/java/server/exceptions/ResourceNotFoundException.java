package server.exceptions;

public class ResourceNotFoundException extends ServerResponseException {
    public ResourceNotFoundException(final String message) {
        super(404, "Não foi possivel encontrar: '%s'.".formatted(message));
    }
}
