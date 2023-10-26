package server.exceptions;

public class BadRequestException extends ServerResponseException {

    public BadRequestException(final String mensagem) {
        super(400, mensagem);
    }
}
