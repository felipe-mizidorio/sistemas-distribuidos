package protocol.request;

import protocol.commons.EmptyPayload;
import protocol.request.header.Header;

public class FindUserRequest extends Request<EmptyPayload>{
    public FindUserRequest(String token) {
        super(new Header(RequestOperations.BUSCAR_USUARIO, token));
    }
}
