package server.processes;

import protocol.request.CreateUserRequest;
import protocol.response.CreateUserResponse;
import protocol.response.Response;
import server.controller.UserController;
import server.datatransferobject.user.CreateUser;
import server.datatransferobject.user.UserDTO;
import server.exceptions.ServerResponseException;

public class CreateUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var createUserRequestReceived = buildRequest(json, CreateUserRequest.class);
        var payload = createUserRequestReceived.getPayload();
        var user = CreateUser.builder()
                .tipo(false)
                .nome(payload.getNome())
                .senha(payload.getSenha())
                .email(payload.getEmail())
                .build();
        UserController controller = UserController.getInstance();
        UserDTO createdUser = controller.createUser(user);
        return new CreateUserResponse(createdUser);
    }
}
