package server.procedure;

import protocol.request.AdminCreateUserRequest;
import protocol.response.AdminCreateUserResponse;
import protocol.response.Response;
import server.datatransferobject.CreateUser;
import server.exceptions.ServerResponseException;
import server.management.UserManager;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminCreateUserProcedure extends ProcedureTemplate {
    public Response<?> doProcedure(String json) throws ServerResponseException {
        var adminCreateUserRequestReceived = buildRequest(json, AdminCreateUserRequest.class);
        var token = adminCreateUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminCreateUserRequestReceived.getPayload();
        var user = CreateUser.builder()
                .tipo(payload.getTipo())
                .nome(payload.getNome())
                .senha(payload.getSenha())
                .email(payload.getEmail())
                .build();
        var createdUser = UserManager.getInstance().createUser(user);
        return new AdminCreateUserResponse(createdUser);
    }
}
