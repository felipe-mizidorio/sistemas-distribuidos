package server.processes;

import jwt.Jwt;
import jwt.validation.ValidateToken;
import protocol.request.DeleteUserRequest;
import protocol.response.DeleteUserResponse;
import protocol.response.Response;
import server.controller.UserController;
import server.datatransferobject.DeleteUser;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;

public class DeleteUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var deleteUserRequestReceived = buildRequest(json, DeleteUserRequest.class);
        var token = deleteUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        var payload = deleteUserRequestReceived.getPayload();
        long idSender = Jwt.getId(deleteUserRequestReceived.getHeader().token());
        boolean isAdmin = Jwt.getAdminStatus(deleteUserRequestReceived.getHeader().token());
        var user = DeleteUser.builder()
                .registroSender(idSender)
                .isSenderAdmin(isAdmin)
                .registroToDelete(idSender)
                .email(payload.getEmail())
                .senha(payload.getSenha())
                .build();
        UserController controller = UserController.getInstance();
        controller.checkCredentials(user);
        controller.deleteUser(user);
        return new DeleteUserResponse();
    }
}
