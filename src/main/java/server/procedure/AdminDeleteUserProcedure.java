package server.procedure;

import jwt.Jwt;
import protocol.request.AdminFindUserRequest;
import protocol.response.AdminDeleteUserResponse;
import protocol.response.Response;
import server.datatransferobject.DeleteUser;
import server.exceptions.ServerResponseException;
import server.management.UserManager;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminDeleteUserProcedure extends ProcedureTemplate {

    public Response<?> doProcedure(String json) throws ServerResponseException {
        var adminDeleteUserRequestReceived = buildRequest(json, AdminFindUserRequest.class);
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
        UserManager.getInstance().deleteUser(user);
        return new AdminDeleteUserResponse();
    }
}
