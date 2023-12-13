package server.processes.routes;

import jwt.Jwt;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminDeleteSegmentRequest;
import protocol.response.Response;
import protocol.response.routes.DeleteSegmentResponse;
import protocol.response.routes.DeleteSegmentResponse;
import server.controller.SegmentController;
import server.datatransferobject.segment.DeleteSegment;
import server.datatransferobject.segment.SegmentDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class DeleteSegmentProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminDeleteSegmentRequestReceived = buildRequest(json, AdminDeleteSegmentRequest.class);
        String token = adminDeleteSegmentRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminDeleteSegmentRequestReceived.getPayload();
        long idSender = Jwt.getId(adminDeleteSegmentRequestReceived.getHeader().token());
        boolean isAdmin = Jwt.getAdminStatus(adminDeleteSegmentRequestReceived.getHeader().token());
        var segment = DeleteSegment.builder()
                .registroSender(idSender)
                .isSenderAdmin(isAdmin)
                .pdi_inicial(payload.getPdi_inicial())
                .pdi_final(payload.getPdi_final())
                .build();
        var inverseSegment = DeleteSegment.builder()
                .registroSender(idSender)
                .isSenderAdmin(isAdmin)
                .pdi_inicial(payload.getPdi_final())
                .pdi_final(payload.getPdi_inicial())
                .build();
        SegmentController segmentController = SegmentController.getInstance();
        segmentController.deleteSegment(segment);
        segmentController.deleteSegment(inverseSegment);
        return new DeleteSegmentResponse(segment.getPdi_inicial(), segment.getPdi_final());
    }
}
