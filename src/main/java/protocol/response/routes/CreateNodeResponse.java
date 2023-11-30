package protocol.response.routes;

import protocol.response.Response;
import server.datatransferobject.node.NodeDTO;

public class CreateNodeResponse implements Response<NodeDTO> {
    private final NodeDTO payload;

    public CreateNodeResponse(NodeDTO payload) {
        this.payload = payload;
    }

    public NodeDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "CreateNodeResponse{" +
                "payload=" + payload +
                '}';
    }
}
