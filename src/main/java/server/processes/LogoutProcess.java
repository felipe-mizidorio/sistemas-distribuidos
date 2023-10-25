package server.procedure;

import protocol.request.LogoutRequest;
import protocol.response.LogoutResponse;
import protocol.response.Response;
import server.exceptions.ServerResponseException;
import jwt.validation.ValidateToken;

public class LogoutProcedure extends ProcedureTemplate {

    public Response<?> doProcedure(String json) throws ServerResponseException {
        var logoutRequestReceived = buildRequest(json, LogoutRequest.class);
        var token = logoutRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        return new LogoutResponse();
    }
}
