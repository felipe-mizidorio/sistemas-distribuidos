package protocol.request.admin;

import protocol.commons.EmptyPayload;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

public class AdminFindUsersRequest extends Request<EmptyPayload> {
    public AdminFindUsersRequest(final String token) {
        super(new Header(RequestOperations.ADMIN_BUSCAR_USUARIOS, token));
    }
}
