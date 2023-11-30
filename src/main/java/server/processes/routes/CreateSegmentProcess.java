package server.processes.routes;

import euclidean.EuclideanDistance;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminCreateSegmentRequest;
import protocol.response.Response;
import protocol.response.routes.CreateSegmentResponse;
import server.controller.NodeController;
import server.controller.SegmentController;
import server.datatransferobject.segment.CreateSegment;
import server.datatransferobject.segment.SegmentDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class CreateSegmentProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminCreateSegmentRequestReceived = buildRequest(json, AdminCreateSegmentRequest.class);
        String token = adminCreateSegmentRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminCreateSegmentRequestReceived.getPayload();
        var PdiInicial = NodeController.getInstance().findNode(payload.getPdiInicial());
        var PdiFinal = NodeController.getInstance().findNode(payload.getPdiInicial());
        var segment = CreateSegment.builder()
                .pdiInicial(payload.getPdiInicial())
                .pdiFinal(payload.getPdiFinal())
                .distancia(EuclideanDistance.calculateDistance(PdiInicial.getPosicao().x(), PdiFinal.getPosicao().x(),
                        PdiInicial.getPosicao().y(), PdiFinal.getPosicao().y()))
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        SegmentController segmentController = SegmentController.getInstance();
        SegmentDTO segmentCreated = segmentController.createSegment(segment);
        return new CreateSegmentResponse(segmentCreated);
    }
}
