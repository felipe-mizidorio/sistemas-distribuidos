package server.procedure;

import protocol.request.AdminFindUserRequest;
import protocol.response.FindUserResponse;
import protocol.response.Response;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;
import server.management.UserManager;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminFindUserProcedure extends ProcedureTemplate {
    public Response<?> doProcedure(String json) throws ServerResponseException {
        var adminFindUserRequestReceived = buildRequest(json, AdminFindUserRequest.class);
        var token = adminFindUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        UserManager controller = UserManager.getInstance();
        UserDTO user = controller.findUser(adminFindUserRequestReceived.getPayload().getRegistro());
        return new FindUserResponse(user);
    }
}
