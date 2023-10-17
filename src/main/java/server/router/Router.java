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
import json.validation.ValidationHelper;
import server.procedure.Procedure;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Router {
    private Map<String, Procedure> routes;

    public static RouterBuilder builder() {
        return new RouterBuilder();
    }

    public Response<?> serve(final String stringRequest) throws ServerResponseException {
        EmptyRequest req;
        try {
            req = Json.fromJson(stringRequest, EmptyRequest.class);
            ValidationHelper.validate(req);
        } catch (JsonSyntaxException e) {
            throw new BadRequestException("Invalid header");
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        }

        var procedure = routes.get(req.getHeader().operation());
        if (procedure == null) {
            throw new MethodNotAllowedException(req.getHeader().operation());
        }

        return procedure.doProcedure(stringRequest);
    }

    public static class RouterBuilder {
        @NonNull
        private final Map<String, Procedure> routes = new HashMap<>();

        public RouterBuilder addRoute(String operation, Procedure handler) {
            routes.put(operation, handler);
            return this;
        }

        public Router build() {
            return new Router(routes);
        }
    }
}
