package protocol.request.routes;

import protocol.commons.EmptyPayload;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

public class AdminFindSegmentsRequest extends Request<EmptyPayload>{
    public AdminFindSegmentsRequest(String token) {
        super(new Header(RequestOperations.BUSCAR_SEGMENTOS, token));
    }
}
