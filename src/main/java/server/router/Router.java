package server.router;

import com.google.gson.JsonSyntaxException;
import json.Json;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import protocol.request.EmptyRequest;
import protocol.response.Response;
import server.exceptions.BadRequestException;
import server.exceptions.MethodNotAllowedException;
import server.exceptions.ServerResponseException;
import json.validation.ConstraintViolated;
import json.validation.ValidateJson;
import server.processes.Process;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Router {
    private Map<String, Process> routes;

    public static RouterBuilder builder() {
        return new RouterBuilder();
    }

    public Response<?> serve(final String stringRequest) throws ServerResponseException {
        EmptyRequest req;
        try {
            req = Json.fromJson(stringRequest, EmptyRequest.class);
            ValidateJson.validate(req);
        } catch (JsonSyntaxException e) {
            throw new BadRequestException("ERRO: Header inválido");
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        }

        var operation = routes.get(req.getHeader().operation());
        if (operation == null) {
            throw new MethodNotAllowedException(req.getHeader().operation());
        }

        return operation.execute(stringRequest);
    }

    public static class RouterBuilder {
        @NonNull
        private final Map<String, Process> routes = new HashMap<>();

        public RouterBuilder addRoute(String operation, Process handler) {
            routes.put(operation, handler);
            return this;
        }

        public Router build() {
            return new Router(routes);
        }
    }
}
