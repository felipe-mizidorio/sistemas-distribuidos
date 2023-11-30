package server.processes.routes;

import euclidean.EuclideanDistance;
import jwt.Jwt;
import jwt.validation.ValidateAdmin;
import protocol.request.routes.AdminUpdateSegmentRequest;
import protocol.response.Response;
import protocol.response.routes.UpdateSegmentResponse;
import server.controller.NodeController;
import server.controller.SegmentController;
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
        var pdiInicial = NodeController.getInstance().findNode(payload.getPdiInicial());
        var pdiFinal = NodeController.getInstance().findNode(payload.getPdiFinal());
        var user = UpdateSegment.builder()
                .registroSender(idSender)
                .pdiInicial(payload.getPdiInicial())
                .pdiFinal(payload.getPdiFinal())
                .distancia(EuclideanDistance.calculateDistance(pdiInicial.getPosicao().x(), pdiInicial.getPosicao().y(), pdiFinal.getPosicao().x(), pdiFinal.getPosicao().y()))
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        SegmentController controller = SegmentController.getInstance();
        SegmentDTO updatedSegment = controller.updateSegment(user);
        return new UpdateSegmentResponse(updatedSegment);
    }
}
