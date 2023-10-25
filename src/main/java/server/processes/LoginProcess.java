package server.procedure;

import protocol.request.LoginRequest;
import protocol.response.LoginResponse;
import protocol.response.Response;
import server.exceptions.ServerResponseException;
import server.management.UserManager;

public class LoginProcedure extends ProcedureTemplate{
    public Response<?> doProcedure(String json) throws ServerResponseException {
        var loginRequestReceived = buildRequest(json, LoginRequest.class);
        String userToken = UserManager.getInstance().login(loginRequestReceived.getPayload());
        return new LoginResponse(userToken);
    }
}
