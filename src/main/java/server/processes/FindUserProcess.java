package server.processes;

import jwt.Jwt;
import jwt.validation.ValidateToken;
import protocol.request.FindUserRequest;
import protocol.response.FindUserResponse;
import protocol.response.Response;
import server.controller.UserController;
import server.datatransferobject.UserDTO;
import server.exceptions.ServerResponseException;

public class FindUserProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var findUserRequestReceived = buildRequest(json, FindUserRequest.class);
        var token = findUserRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        UserController controller = UserController.getInstance();
        UserDTO user = controller.findUser(Jwt.getId(findUserRequestReceived.getHeader().token()));
        return new FindUserResponse(user);
    }
}
