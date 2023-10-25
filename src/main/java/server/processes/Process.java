package server.procedure;

import protocol.response.Response;
import server.exceptions.ServerResponseException;

public interface Procedure {
    <T> T buildRequest(String json, Class<T> className) throws ServerResponseException;

    Response<?> doProcedure(String json) throws ServerResponseException;
}
