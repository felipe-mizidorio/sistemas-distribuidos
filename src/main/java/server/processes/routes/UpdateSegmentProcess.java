package server.processes.routes;

import calculus.euclidean.EuclideanDistance;
import jwt.Jwt;
import jwt.validation.ValidateAdmin;
import protocol.request.routes.AdminUpdateSegmentRequest;
import protocol.response.Response;
import protocol.response.routes.UpdateSegmentResponse;
import server.controller.NodeController;
import server.controller.SegmentController;
import server.datatransferobject.node.NodeDTO;
import server.datatransferobject.segment.SegmentDTO;
import server.datatransferobject.segment.UpdateSegment;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class UpdateSegmentProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var AdminUpdateSegmentRequestReceived = buildRequest(json, AdminUpdateSegmentRequest.class);
        var token = AdminUpdateSegmentRequestReceived.getHeader().token();
        ValidateAdmin.validate(token);
        var payload = AdminUpdateSegmentRequestReceived.getPayload();
        long idSender = Jwt.getId(AdminUpdateSegmentRequestReceived.getHeader().token());
        NodeDTO pdiInicial = NodeController.getInstance().findNode(payload.getPdi_inicial());
        NodeDTO pdiFinal = NodeController.getInstance().findNode(payload.getPdi_final());
        var segment = UpdateSegment.builder()
                .registroSender(idSender)
                .pdi_inicial(payload.getPdi_inicial())
                .pdi_final(payload.getPdi_final())
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        var inverseSegment = UpdateSegment.builder()
                .registroSender(idSender)
                .pdi_inicial(payload.getPdi_final())
                .pdi_final(payload.getPdi_inicial())
                .aviso(null)
                .acessivel(payload.getAcessivel())
                .build();
        SegmentController controller = SegmentController.getInstance();
        SegmentDTO updatedSegment = controller.updateSegment(segment);
        controller.updateSegment(inverseSegment);
        return new UpdateSegmentResponse(updatedSegment);
    }
}
