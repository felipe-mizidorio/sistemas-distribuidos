package server.processes.routes;

import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminFindSegmentsRequest;
import protocol.response.Response;
import protocol.response.routes.FindSegmentsResponse;
import server.controller.SegmentController;
import server.datatransferobject.segment.SegmentDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

import java.util.List;

public class FindSegmentsProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminFindSegmentsRequestReceived = buildRequest(json, AdminFindSegmentsRequest.class);
        var token = adminFindSegmentsRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        SegmentController controller = SegmentController.getInstance();
        List<SegmentDTO> segments = controller.findSegments();
        return new FindSegmentsResponse(segments);
    }
}
