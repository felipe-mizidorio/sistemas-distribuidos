package protocol.request;

import protocol.commons.EmptyPayload;
import protocol.request.header.Header;

public class LogoutRequest extends Request<EmptyPayload> {
    public LogoutRequest(final String userToken) {
        super(new Header(RequestOperations.LOGOUT, userToken));
    }
}
