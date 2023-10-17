package server.exceptions;

public class MethodNotAllowedException extends ServerResponseException {
    public MethodNotAllowedException(final String method) {
        super(405, "'%s' não reconhecido".formatted(method));
    }
}
