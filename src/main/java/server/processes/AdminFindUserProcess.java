package server.processes;

import protocol.request.AdminFindUserRequest;
import protocol.response.FindUserResponse;
import protocol.response.Response;
import server.datatransferobject.user.UserDTO;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminFindUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminFindUserRequestReceived = buildRequest(json, AdminFindUserRequest.class);
        var token = adminFindUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        UserController controller = UserController.getInstance();
        UserDTO user = controller.findUser(adminFindUserRequestReceived.getPayload().getRegistro());
        return new FindUserResponse(user);
    }
}
