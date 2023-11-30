package server.processes;

import jwt.Jwt;
import jwt.validation.ValidateToken;
import protocol.request.UpdateUserRequest;
import protocol.response.Response;
import protocol.response.UpdateUserResponse;
import server.controller.UserController;
import server.datatransferobject.user.UpdateUser;
import server.datatransferobject.user.UserDTO;
import server.exceptions.ServerResponseException;

public class UpdateUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var updateUserRequestReceived = buildRequest(json, UpdateUserRequest.class);
        var token = updateUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        var payload = updateUserRequestReceived.getPayload();
        var user = UpdateUser.builder()
                .registro(Jwt.getId(token))
                .nome(payload.getNome())
                .email(payload.getEmail())
                .senha(payload.getSenha())
                .tipo(Jwt.getAdminStatus(token))
                .build();
        UserController controller = UserController.getInstance();
        UserDTO updatedUser = controller.updateUser(user);
        return new UpdateUserResponse(updatedUser);
    }
}
