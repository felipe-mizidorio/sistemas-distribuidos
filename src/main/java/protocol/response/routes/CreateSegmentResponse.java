package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import protocol.response.Response;
import server.datatransferobject.segment.SegmentDTO;

public class CreateSegmentResponse implements Response<SegmentDTO> {
    @Valid
    @NotNull
    private final SegmentDTO payload;

    public CreateSegmentResponse(SegmentDTO payload) {
        this.payload = payload;
    }

    public SegmentDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "CreateSegmentResponse{" +
                "payload=" + payload +
                '}';
    }
}
