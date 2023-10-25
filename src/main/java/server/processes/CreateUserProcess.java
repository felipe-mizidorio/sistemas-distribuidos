package server.processes;

import jwt.validation.ValidateToken;
import protocol.request.CreateUserRequest;
import protocol.response.CreateUserResponse;
import protocol.response.Response;
import server.controller.UserController;
import server.datatransferobject.CreateUser;
import server.exceptions.ServerResponseException;

public class CreateUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var createUserRequestReceived = buildRequest(json, CreateUserRequest.class);
        var token = createUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        var payload = createUserRequestReceived.getPayload();
        var user = CreateUser.builder()
                .tipo(payload.getTipo())
                .nome(payload.getNome())
                .senha(payload.getSenha())
                .email(payload.getEmail())
                .build();
        var createdUser = UserController.getInstance().createUser(user);
        return new CreateUserResponse(createdUser);
    }
}
