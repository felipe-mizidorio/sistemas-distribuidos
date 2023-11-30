package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.response.Response;
import server.datatransferobject.node.NodeDTO;

import java.util.List;

public class FindNodesResponse implements Response<FindNodesResponse.Payload> {
    @Valid
    @NotNull
    private final FindNodesResponse.Payload payload;

    public FindNodesResponse(List<NodeDTO> pdis) {
        this.payload = new FindNodesResponse.Payload(pdis);
    }

    public FindNodesResponse(FindNodesResponse.Payload payload) {
        this.payload = payload;
    }

    @Override
    public FindNodesResponse.Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "FindNodesResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        private final List<NodeDTO> pdis;

        public Payload(List<NodeDTO> pdis) {
            this.pdis = pdis;
        }

        @Override
        public String toString() {
            return "{ pdis=" + pdis +
                    '}';
        }
    }
}
