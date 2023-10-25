package server.procedure;

import protocol.request.AdminUpdateUserRequest;
import protocol.response.AdminUpdateUserResponse;
import protocol.response.Response;
import server.datatransferobject.UpdateUser;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;
import server.management.UserManager;
import jwt.validation.ValidateAdmin;

public class AdminUpdateUserProcedure extends ProcedureTemplate {
    public Response<?> doProcedure(String json) throws ServerResponseException {
        var AdminUpdateUserRequestReceived = buildRequest(json, AdminUpdateUserRequest.class);
        var token = AdminUpdateUserRequestReceived.getHeader().token();
        ValidateAdmin.validate(token);
        UserManager controller = UserManager.getInstance();
        var payload = AdminUpdateUserRequestReceived.getPayload();
        var user = UpdateUser.builder()
                .senha(payload.getSenha())
                .email(payload.getEmail())
                .nome(payload.getNome())
                .tipo(payload.getTipo())
                .registro(payload.getRegistro())
                .build();
        UserDTO updatedUser = controller.updateUser(user);
        return new AdminUpdateUserResponse(updatedUser);
    }
}
