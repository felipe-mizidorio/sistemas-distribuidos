package server.exceptions;

public class ResourceNotFoundException extends ServerResponseException {
    public ResourceNotFoundException(final String messagem) {
        super(404, "Não foi possivel encontrar: '%s'.".formatted(messagem));
    }
}
