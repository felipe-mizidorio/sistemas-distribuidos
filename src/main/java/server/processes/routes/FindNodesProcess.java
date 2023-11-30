package server.processes.routes;

import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;
import protocol.response.Response;
import protocol.request.routes.AdminFindNodesRequest;
import protocol.response.routes.FindNodesResponse;
import server.controller.NodeController;
import server.datatransferobject.node.NodeDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

import java.util.List;

public class FindNodesProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminFindNodesRequestReceived = buildRequest(json, AdminFindNodesRequest.class);
        var token = adminFindNodesRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        NodeController controller = NodeController.getInstance();
        List<NodeDTO> nodes = controller.findNodes();
        return new FindNodesResponse(nodes);
    }
}
