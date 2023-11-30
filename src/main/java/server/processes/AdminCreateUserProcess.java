package server.processes;

import protocol.request.AdminCreateUserRequest;
import protocol.response.CreateUserResponse;
import protocol.response.Response;
import server.datatransferobject.user.CreateUser;
import server.datatransferobject.user.UserDTO;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import jwt.validation.ValidateAdmin;
import jwt.validation.ValidateToken;

public class AdminCreateUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var adminCreateUserRequestReceived = buildRequest(json, AdminCreateUserRequest.class);
        var token = adminCreateUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        ValidateAdmin.validate(token);
        var payload = adminCreateUserRequestReceived.getPayload();
        var user = CreateUser.builder()
                .tipo(true)
                .nome(payload.getNome())
                .senha(payload.getSenha())
                .email(payload.getEmail())
                .build();
        UserController controller = UserController.getInstance();
        UserDTO createdUser = controller.createUser(user);
        return new CreateUserResponse(createdUser);
    }
}
