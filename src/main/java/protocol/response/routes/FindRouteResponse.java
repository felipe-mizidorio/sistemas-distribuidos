package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.response.Response;
import server.datatransferobject.route.RouteDTO;

import java.util.List;

public class FindRouteResponse implements Response<FindRouteResponse.Payload> {
    @Valid
    @NotNull
    private final FindRouteResponse.Payload payload;

    public FindRouteResponse(List<RouteDTO> routes) {
        this.payload = new FindRouteResponse.Payload(routes);
    }

    public FindRouteResponse(FindRouteResponse.Payload payload) {
        this.payload = payload;
    }

    @Override
    public FindRouteResponse.Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "FindRouteResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        private final List<RouteDTO> comandos;

        public Payload(List<RouteDTO> comandos) {
            this.comandos = comandos;
        }

        @Override
        public String toString() {
            return "{ comandos=" + comandos +
                    '}';
        }
    }
}
