package server.processes;

import protocol.request.LoginRequest;
import protocol.response.LoginResponse;
import protocol.response.Response;
import server.exceptions.ServerResponseException;
import server.controller.UserController;

public class LoginProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var loginRequestReceived = buildRequest(json, LoginRequest.class);
        UserController controller = UserController.getInstance();
        String userToken = controller.login(loginRequestReceived.getPayload());
        return new LoginResponse(userToken);
    }
}
