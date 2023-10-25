package server.procedure;

import protocol.request.AdminFindUsersRequest;
import protocol.response.FindUsersResponse;
import protocol.response.Response;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;
import server.management.UserManager;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

import java.util.List;

public class AdminFindUsersProcedure extends ProcedureTemplate {
    public Response<?> doProcedure(String json) throws ServerResponseException {
        var AdminFindUsersRequestReceived = buildRequest(json, AdminFindUsersRequest.class);
        var token = AdminFindUsersRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        UserManager controller = UserManager.getInstance();
        List<UserDTO> users = controller.findUsers();
        return new FindUsersResponse(users);
    }
}
