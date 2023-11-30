package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import protocol.response.Response;
import server.datatransferobject.node.NodeDTO;
import server.entity.Node;

public class UpdateNodeResponse implements Response<NodeDTO> {
    @Valid
    @NotNull
    private final NodeDTO payload;

    public UpdateNodeResponse(NodeDTO payload) {
        this.payload = payload;
    }

    public static UpdateNodeResponse of(Node node) {
        return new UpdateNodeResponse(NodeDTO.of(node));
    }

    @Override
    public NodeDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "UpdateNodeResponse{" +
                "payload=" + payload +
                '}';
    }
}
