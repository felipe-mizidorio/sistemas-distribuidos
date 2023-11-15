package server.processes;

import com.auth0.jwt.JWT;
import json.Json;
import jwt.Jwt;
import protocol.request.AdminUpdateUserRequest;
import protocol.response.Response;
import protocol.response.UpdateUserResponse;
import server.datatransferobject.UpdateUser;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import jwt.validation.ValidateAdmin;

public class AdminUpdateUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var AdminUpdateUserRequestReceived = buildRequest(json, AdminUpdateUserRequest.class);
        var token = AdminUpdateUserRequestReceived.getHeader().token();
        ValidateAdmin.validate(token);
        var payload = AdminUpdateUserRequestReceived.getPayload();
        var user = UpdateUser.builder()
                .sender(Jwt.getId(token))
                .registro(payload.getRegistro())
                .nome(payload.getNome())
                .email(payload.getEmail())
                .senha(payload.getSenha())
                .tipo(payload.getTipo())
                .build();
        UserController controller = UserController.getInstance();
        UserDTO updatedUser = controller.updateUser(user);
        return new UpdateUserResponse(updatedUser);
    }
}
