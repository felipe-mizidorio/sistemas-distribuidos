package server.processes.routes;

import jwt.Jwt;
import jwt.validation.ValidateAdmin;
import protocol.request.routes.AdminUpdateNodeRequest;
import protocol.response.Response;
import protocol.response.routes.UpdateNodeResponse;
import server.controller.NodeController;
import server.datatransferobject.node.NodeDTO;
import server.datatransferobject.node.UpdateNode;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class UpdateNodeProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var AdminUpdateNodeRequestReceived = buildRequest(json, AdminUpdateNodeRequest.class);
        var token = AdminUpdateNodeRequestReceived.getHeader().token();
        ValidateAdmin.validate(token);
        var payload = AdminUpdateNodeRequestReceived.getPayload();
        long idSender = Jwt.getId(AdminUpdateNodeRequestReceived.getHeader().token());
        var user = UpdateNode.builder()
                .registroSender(idSender)
                .id(payload.getId())
                .nome(payload.getNome())
                .posicaoX(payload.getPosicao().x())
                .posicaoY(payload.getPosicao().y())
                .aviso(payload.getAviso())
                .acessivel(payload.getAcessivel())
                .build();
        NodeController controller = NodeController.getInstance();
        NodeDTO updatedNode = controller.updateNode(user);
        return new UpdateNodeResponse(updatedNode);
    }
}
