package server.processes.routes;

import calculus.euclidean.EuclideanDistance;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminCreateSegmentRequest;
import protocol.response.Response;
import protocol.response.routes.CreateSegmentResponse;
import server.controller.NodeController;
import server.controller.SegmentController;
import server.datatransferobject.node.NodeDTO;
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
        NodeDTO PdiInicial = NodeController.getInstance().findNode(payload.getPdi_inicial());
        NodeDTO PdiFinal = NodeController.getInstance().findNode(payload.getPdi_final());
        var segment = CreateSegment.builder()
                .pdi_inicial(payload.getPdi_inicial())
                .pdi_final(payload.getPdi_final())
                .distancia(EuclideanDistance.calculateDistance(PdiInicial.getPosicao().x(), PdiInicial.getPosicao().y(),
                        PdiFinal.getPosicao().x(), PdiFinal.getPosicao().y()))
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        var inverseSegment = CreateSegment.builder()
                .pdi_inicial(payload.getPdi_final())
                .pdi_final(payload.getPdi_inicial())
                .distancia(EuclideanDistance.calculateDistance(PdiInicial.getPosicao().x(), PdiInicial.getPosicao().y(),
                        PdiFinal.getPosicao().x(), PdiFinal.getPosicao().y()))
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        SegmentController segmentController = SegmentController.getInstance();
        SegmentDTO segmentCreated = segmentController.createSegment(segment);
        SegmentDTO inverseSegmentCreated = segmentController.createSegment(inverseSegment);
        return new CreateSegmentResponse(segmentCreated);
    }
}
