package server.processes;

import protocol.request.LogoutRequest;
import protocol.response.LogoutResponse;
import protocol.response.Response;
import server.exceptions.ServerResponseException;
import jwt.validation.ValidateToken;

public class LogoutProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
//        var logoutRequestReceived = buildRequest(json, LogoutRequest.class);
//        var token = logoutRequestReceived.getHeader().token();
        //ValidateToken.validate(token);
        return new LogoutResponse();
    }
}
