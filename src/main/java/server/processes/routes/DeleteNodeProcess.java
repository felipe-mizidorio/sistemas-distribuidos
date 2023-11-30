package server.processes.routes;

import jwt.Jwt;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.request.routes.AdminDeleteNodeRequest;
import protocol.response.Response;
import protocol.response.routes.DeleteNodeResponse;
import server.controller.NodeController;
import server.datatransferobject.node.DeleteNode;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

public class DeleteNodeProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminDeleteNodeRequestReceived = buildRequest(json, AdminDeleteNodeRequest.class);
        String token = adminDeleteNodeRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminDeleteNodeRequestReceived.getPayload();
        long idSender = Jwt.getId(adminDeleteNodeRequestReceived.getHeader().token());
        boolean isAdmin = Jwt.getAdminStatus(adminDeleteNodeRequestReceived.getHeader().token());
        var node = DeleteNode.builder()
                .registroSender(idSender)
                .isSenderAdmin(isAdmin)
                .nodeToDelete(payload.getId())
                .build();
        NodeController nodeController = NodeController.getInstance();
        nodeController.findNode(node.getNodeToDelete());
        nodeController.deleteNode(node);
        return new DeleteNodeResponse(node.getNodeToDelete());
    }
}
