package server.processes.routes;

import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminCreateNodeRequest;
import protocol.response.Response;
import protocol.response.routes.CreateNodeResponse;
import server.controller.NodeController;
import server.datatransferobject.node.CreateNode;
import server.datatransferobject.node.NodeDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class CreateNodeProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminCreateNodeRequestReceived = buildRequest(json, AdminCreateNodeRequest.class);
        var token = adminCreateNodeRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminCreateNodeRequestReceived.getPayload();
        var node = CreateNode.builder()
                .nome(payload.getNome())
                .posicaoX(payload.getPosicao().getX())
                .posicaoY(payload.getPosicao().getY())
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        NodeController nodeController = NodeController.getInstance();
        NodeDTO nodeCreated = nodeController.createNode(node);
        return new CreateNodeResponse(nodeCreated);
    }
}
