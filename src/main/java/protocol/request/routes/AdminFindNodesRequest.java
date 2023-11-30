package protocol.request.routes;

import protocol.commons.EmptyPayload;
import protocol.request.EmptyRequest;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

public class AdminFindNodesRequest extends Request<EmptyPayload>{

    public AdminFindNodesRequest(String token) {
        super(new Header(RequestOperations.BUSCAR_PDIS, token));
    }
}
