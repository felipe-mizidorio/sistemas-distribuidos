package server.processes;

import protocol.request.AdminFindUsersRequest;
import protocol.response.FindUsersResponse;
import protocol.response.Response;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

import java.util.List;

public class AdminFindUsersProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var AdminFindUsersRequestReceived = buildRequest(json, AdminFindUsersRequest.class);
        var token = AdminFindUsersRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        UserController controller = UserController.getInstance();
        List<UserDTO> usuarios = controller.findUsers();
        return new FindUsersResponse(usuarios);
    }
}
