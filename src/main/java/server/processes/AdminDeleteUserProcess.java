package server.processes;

import jwt.Jwt;
import protocol.request.AdminDeleteUserRequest;
import protocol.response.DeleteUserResponse;
import protocol.response.Response;
import server.datatransferobject.user.DeleteUser;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminDeleteUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminDeleteUserRequestReceived = buildRequest(json, AdminDeleteUserRequest.class);
        var token = adminDeleteUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminDeleteUserRequestReceived.getPayload();
        long idSender = Jwt.getId(adminDeleteUserRequestReceived.getHeader().token());
        boolean isAdmin = Jwt.getAdminStatus(adminDeleteUserRequestReceived.getHeader().token());
        var user = DeleteUser.builder()
                .registroSender(idSender)
                .registroToDelete(payload.getRegistro())
                .isSenderAdmin(isAdmin)
                .build();
        UserController controller = UserController.getInstance();
        controller.findUser(user.getRegistroToDelete());
        controller.deleteUser(user);
        return new DeleteUserResponse(payload.getRegistro());
    }
}
