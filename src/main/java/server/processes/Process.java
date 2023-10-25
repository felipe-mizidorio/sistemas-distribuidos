package server.processes;

import protocol.response.Response;
import server.exceptions.ServerResponseException;

public interface Process {
    <T> T buildRequest(String json, Class<T> className) throws ServerResponseException;

    Response<?> execute(String json) throws ServerResponseException;
}
