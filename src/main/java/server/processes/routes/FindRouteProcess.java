package server.processes.routes;

import calculus.route.Routes;
import jwt.validation.ValidateToken;
import protocol.request.routes.FindRouteRequest;
import protocol.response.Response;
import protocol.response.routes.FindRouteResponse;
import server.datatransferobject.route.RouteDTO;
import server.exceptions.ServerResponseException;
import server.processes.ProcessTemplate;

import java.util.List;

public class FindRouteProcess extends ProcessTemplate {
    public Response<?> execute(String json) throws ServerResponseException {
        var findRouteRequestReceived = buildRequest(json, FindRouteRequest.class);
        var token = findRouteRequestReceived.getHeader().token();
        ValidateToken.validate(token);
        Long pdi_inicial = findRouteRequestReceived.getPayload().getPdi_inicial();
        Long pdi_final = findRouteRequestReceived.getPayload().getPdi_final();
        List<RouteDTO> routes = Routes.calculateRoute(pdi_inicial, pdi_final);
        return new FindRouteResponse(routes);
    }
}
